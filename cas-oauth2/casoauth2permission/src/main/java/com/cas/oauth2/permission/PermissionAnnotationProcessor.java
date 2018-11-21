package com.cas.oauth2.permission;

import com.cas.oauth2.permission.anno.PermissionDependency;
import com.cas.oauth2.permission.anno.PreAuthorize;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

//import org.springframework.security.access.prepost.PreAuthorize;

//该类可处理的注解类型
//@SupportedAnnotationTypes({"com.cas.oauth2.permission.anno.PreAuthorize","com.cas.oauth2.permission.anno.PermissionDependency"})
//指定编译的JDK版本
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class PermissionAnnotationProcessor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<String>();

        annotations.add(PermissionDependency.class.getCanonicalName());
        annotations.add(PreAuthorize.class.getCanonicalName());
        return annotations;
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> genPermissionDependencies = roundEnvironment.getElementsAnnotatedWith(PermissionDependency.class);
        Set<? extends Element> genPreAuthorizes = roundEnvironment.getElementsAnnotatedWith(PreAuthorize.class);

        //module patent-module
        Map<Method, Authority> moduleParentMap = new HashMap<>();

        //module dependency-module
        Map<Method, Set<String>> dependenciesMap = new HashMap<>();

        if (genPreAuthorizes.size() == 0)
            return true;

        for (Element element : genPreAuthorizes) {
            //get preAuthorize value
            PreAuthorize preAuthorize = element.getAnnotation(PreAuthorize.class);

            String hasAuthorize = preAuthorize.value();
            // TODO: 18-10-17 use regex to get authority
            String authorize = hasAuthorize.split("'")[1].trim();
            Authority authority = new Authority(authorize);

            if (element.getKind() == ElementKind.CLASS) {
                element.getEnclosedElements()
                        .stream()
                        .filter(e -> !e.getSimpleName().toString().contains("<"))
                        .forEach(e ->
                                moduleParentMap.putIfAbsent(new Method(element.toString(),
                                        e.getSimpleName().toString()), authority
                                )
                        );
            } else {
                moduleParentMap.putIfAbsent(new Method(element.getEnclosingElement().toString(),
                        element.getSimpleName().toString()), authority);
            }

        }

        if (genPermissionDependencies.size() > 0) {

            for (Element element : genPermissionDependencies) {
                PermissionDependency preAuthorize = element.getAnnotation(PermissionDependency.class);

                if (element.getKind() == ElementKind.METHOD) {

                    String classNamePre = element.getEnclosingElement().toString();
                    Method method = new Method(classNamePre, element.getSimpleName().toString());

                    Set<String> dependencies = new HashSet<>(Arrays.asList(preAuthorize.value()));

                    if (dependenciesMap.containsKey(method)) {
                        dependenciesMap.get(method).addAll(dependencies);
//                        dependenciesMap.put(method, );
                    } else {
                        dependenciesMap.put(method, dependencies);
                    }
                } else {
                    element.getEnclosedElements()
                            .stream()
                            .filter(e -> !e.getSimpleName().toString().contains("<"))
                            .forEach(e -> {
                                Method method = new Method(element.toString(), e.getSimpleName().toString());

                                Set<String> dependencies = new HashSet<>(Arrays.asList(preAuthorize.value()));

                                if (dependenciesMap.containsKey(method)) {
                                    dependenciesMap.get(method).addAll(dependencies);
                                } else {
                                    dependenciesMap.put(method, dependencies);
                                }
                            });
                }
            }
        }

        /*
        test.user.extra

        test.user.admin

        sys
        sys.org

        ---------------------
        sys
        sys.org
        test
        test.user
        test.user.admin
        test.user.extra
         */
//        Set<String> codes = new HashSet<>();


        //dependency
        Map<Authority, Set<String>> dependencies = new HashMap<>();

        moduleParentMap.forEach((key, e) -> {
            Set<String> de = dependenciesMap.get(key);

            dependencies.putIfAbsent(e, de);
        });

        // code parent
        List<Module> modules = moduleParentMap.values()
                .stream()
                .distinct()
                .flatMap(a -> {
                    HashSet<Module> as = new HashSet<>();

                    String[] t = a.code.split("\\.");
                    for (int i = 0; i < t.length; i++) {
                        as.add(new Module(String.join(".", Arrays.copyOfRange(t, 0, i + 1))));
                    }

                    return as.stream();
                })
                .distinct()
                .sorted((a1, a2) -> {
                    if (a1.level == a2.level)
                        return a1.code.compareTo(a2.code);
                    else
                        return a1.level - a2.level;
                })
                .collect(Collectors.toList());


        //action
        List<String> actions = moduleParentMap.values()
                .stream()
                .map(a -> a.action)
                .distinct()
                .collect(Collectors.toList());


        //生成sql
        try {
            FileObject fo = filer.createResource(StandardLocation.locationFor("SOURCE_OUTPUT"), "", "permission_modules.txt");
            Writer w = fo.openWriter();
            w.write("-- delete from authority;");
            w.write("\r\n\r\n");

//  authority sql
            modules.forEach(e -> {
                try {
                    w.write(String.format("insert into authority (code,parent) values ('%s',select id from authority where code = '%s');", e.code, e.parent));
                    w.write("\r\n");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            actions.forEach(e -> {
                try {
                    w.write(String.format("insert into action (name) values ('%s');", e));
                    w.write("\r\n");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            dependencies.forEach((key, e) -> {
                e.forEach(f -> {

                    Authority authority = new Authority(f);

                    try {
                        w.write(String.format("insert into dependency (" +
                                        "authority_id,dependency_id ,aut_action_id , dep_action_id ) " +
                                        "values (select id from authority where code = '%s'," +
                                        "select id from authority where code = '%s'," +
                                        "select id from action where name = '%s'," +
                                        "select id from action where name = '%s'" +
                                        ");",
                                key.code, authority.code, key.action, authority.action));
                        w.write("\r\n");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
            });

            w.flush();
            w.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    //check code whether exist authority table , not exist create sql
    public void checkCode(Set<String> codeOnlyOne, Writer w, String e) {
        boolean exist = codeOnlyOne.contains(e);
        if (!exist) {
            try {
                w.write(String.format("insert into authority (code) values ('%s' );", e));
                w.write("\r\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * -> test.user.extra.read
     * code = test.user.extra
     * action = read
     * parent = test.user
     * <p>
     * code = test.user parent:test
     */
    class Authority {

        /**
         * @param code with action
         */
        public Authority(String code) {

            String[] t = code.split("\\.");

            this.action = t[t.length - 1];

            this.code = String.join(".", Arrays.copyOfRange(t, 0, t.length - 1));
            if (t.length > 2)
                this.parent = String.join(".", Arrays.copyOfRange(t, 0, t.length - 2));
        }

        public String code;
        public String action;

        public String parent;
    }

    class Module {

        /**
         * @param code without action
         */
        public Module(String code) {

            String[] t = code.split("\\.");
            this.level = t.length;
            this.code = code;

            if (t.length > 1)
                this.parent = String.join(".", Arrays.copyOfRange(t, 0, t.length - 1));
        }

        public String code;
        public int level;

        public String parent;

        @Override
        public boolean equals(Object o) {

            if (o == null) {
                return false;
            } else {
                if (this.getClass() == o.getClass()) {
                    Module m = (Module) o;
                    if (this.code.equals(m.code)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }

    class Method {
        public Method(String clazz, String method) {
            this.clazz = clazz;
            this.method = method;
        }

        String clazz;
        String method;

        public String getClazz() {
            return clazz;
        }

        public String getMethod() {
            return method;
        }

        @Override
        public boolean equals(Object o) {

            if (o == null) {
                return false;
            } else {
                if (this.getClass() == o.getClass()) {
                    Method m = (Method) o;
                    if (this.getMethod().equals(m.getMethod()) && this.getClazz().equals(m.getClazz())) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(clazz, method);
        }
    }
}