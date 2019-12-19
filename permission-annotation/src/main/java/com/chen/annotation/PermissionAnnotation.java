package com.chen.annotation;


import com.chen.annotation.anno.Path;
import com.chen.annotation.anno.PermissionDependency;
import com.chen.annotation.anno.PermissionModule;
import com.chen.annotation.anno.PermissionRequest;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * 1.defined permission-annotation
 * 2.defined use permission-annotation
 * 3.debug permission-annotation
 */

@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class PermissionAnnotation extends AbstractProcessor {
    private Filer filer;
    private Messager message;


    //storage module information
    Map<String, Module> modules = new HashMap<>();

    //storage need update java information
    List<Information> information = new ArrayList<>();

    //need update annotation .java path
    String url;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        message = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<String>();

        annotations.add(PermissionModule.class.getCanonicalName());
        annotations.add(PermissionDependency.class.getCanonicalName());
        annotations.add(PermissionRequest.class.getCanonicalName());
        annotations.add(Path.class.getCanonicalName());

        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        //handle PermissionModule because this annotation is the class
        Set<? extends Element> permissionModuleSet = roundEnvironment.getElementsAnnotatedWith(PermissionModule.class);

        Set<? extends Element> pathSet = roundEnvironment.getElementsAnnotatedWith(Path.class);

        //module information have value,but parent is not
        List<Parent> parents = new ArrayList<>();

        //get need upate .java locate
        for (Element e : pathSet) {
            Path path = e.getAnnotation(Path.class);
            url = path.path();
        }

        for (Element e : permissionModuleSet) {

            PermissionModule permissionModule = e.getAnnotation(PermissionModule.class);

            String name = permissionModule.name();
            String id = permissionModule.id();
            String parent = permissionModule.parent();

            PermissionDependency[] dependency = permissionModule.dependencies();

            List<Dependency> list = new ArrayList<>();

            if (dependency.length > 0) {
                for (PermissionDependency d : dependency) {
                    String module = d.module();

                    String action = d.permission().name();
                    if (!action.toLowerCase().equals("read")) {
                        action = "write";
                    }
                    Dependency dependency1 = new Dependency(module, action);
                    list.add(dependency1);
                }
            }

            Module module = new Module(name, parent, id, list);

            modules.put(e.getSimpleName().toString(), module);

            Parent parent1 = new Parent(id);

            if (!parent.equals("") && parent != null) {
                parent1.setParent(parent);
            }

            parents.add(parent1);

        }

        //handle module has parent...
        //parent information storage value
        if (parents.size() > 0) {
//            for (int i = 0; i < parents.size(); i++) {
//                findParent(parents, parents.get(i));
//            }
            fixFindParent(parents);
        }

        modules.forEach((key, it) -> {

            AtomicReference<String> dependency = new AtomicReference<>();
            AtomicReference<String> module = new AtomicReference<>();

            List<Dependency> dependencies = it.getDependencies();

            if (dependencies.size() > 0) {

                dependencies.forEach(j -> {
                    if (dependency.get() != null)
                        dependency.set(dependency.get() + j.name + "." + j.getAction().toLowerCase() + ",");
                    else dependency.set(j.name + "." + j.getAction() + ",");
                });

            }

            parents.forEach(r -> {
                if (r.getName().equals(it.getId())) {
                    if (r.getValue() == null) {
                        module.set(r.getName());
                    } else {
                        module.set(r.getValue());
                    }
                }

            });

            if (dependency.get() != null)
                dependency.set("@PermissionDependency(value ={\"" + dependency.get().substring(0, dependency.get().length() - 1) + "\"})");


            String read = "@PreAuthorize(\"hasAuthority('" + module.get() + ".read" + "')\")";
            String write = "@PreAuthorize(\"hasAuthority('" + module.get() + ".write" + "')\")";

            information.add(new Information(url + key + ".java", url + key + ".java.bak", read, write, dependency.get()));

        });

        //防止生成新的注解.java文件后，在compile时再次生成.java文件
        if (!(information.size() > modules.size())) {
            information.forEach(r -> {
                if (!r.readPath.endsWith(".bak"))
                    write(r.writePath, read(r.dependency, r.read, r.write, r.readPath));
            });
        }


        return true;

    }

    public void fixFindParent(List<Parent> parentList) {
        List<Parent> parentNull = parentList.stream()
                .filter(r -> r.getParent() == null || r.getParent().equals(""))
                .collect(Collectors.toList());


        List<Parent> parents = parentList.stream()
                .filter(r -> r.getParent() != null && !r.getParent().equals(""))
                .collect(Collectors.toList());

        for (int j = 0; j < parents.size(); j++) {
            for (int i = 0; i < parentNull.size(); i++) {

                Parent p = parents.get(j);

                Parent c = parentNull.get(i);

                if (p.getParent().equals(c.getName())) {
                    if (c.getValue() == null)
                        c.setValue(c.getName());

                    p.setValue(c.getValue() + "." + p.getName());

                    p.setParent(null);
                    break;
                }
            }
        }

        parents = parentList.stream().filter(r -> r.getParent() != null && !r.getParent().equals("")).collect(Collectors.toList());

        if (parents.size() > 0)
            fixFindParent(parentList);
        else
            return;


    }


    //find parent
    public void findParent(List<Parent> parents, Parent parent) {

        if (parent.getParent() == null || parent.getParent().equals("")) {
            return;
        }

        parents.stream()
                .filter(r -> r.getName().equals(parent.getParent()))
                .forEach(r -> {
                    if (parent.getParent() != null && !parent.getParent().equals("") && !parent.getParent().equals("null")) {
                        if (parent.getValue() != null) {
                            parent.setValue(r.getName() + "." + parent.getValue());
                        } else {

                            parent.setValue(r.getName() + "." + parent.getName());
                        }
                        parent.setParent(r.getParent());
                    } else {
                        parent.setParent(null);
                    }

                });

        findParent(parents, parent);
    }

    public String read(String dependency, String permissionRead, String permissionWrite, String filePath) {

        BufferedReader br = null;

        String line;

        StringBuffer buf = new StringBuffer();

        try {
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filePath));

            Boolean state = false;

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {

                if (line.trim().startsWith("public class")) {
                    if (state == true && dependency != null) {
                        buf.append(dependency);
                    }

                    state = false;
                    buf.append(line);
                    continue;

                } else if (line.trim().startsWith("@PermissionModule(")) {
                    state = true;
                    continue;

                } else if (line.trim().equals("@PermissionRequest(PermissionRequest.Permission.Read)")) {
                    buf.append(permissionRead);
                    continue;

                } else if (line.trim().startsWith("@PermissionRequest(PermissionRequest.Permission") && !line.equals("@PermissionRequest(PermissionRequest.Permission.Read)")) {
                    buf.append(permissionWrite);
                    continue;
                }

                if (state == false) {
                    buf.append(line);
                }

            }
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }

        }
        return buf.toString();
    }

    /**
     * 将内容回写到文件中
     *
     * @param filePath
     * @param content
     */
    public void write(String filePath, String content) {
        BufferedWriter bw = null;

        try {
            // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath));
            // 将内容写入文件中
            bw.write(content);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();

                } catch (IOException e) {
                    bw = null;

                }

            }

        }

    }

}

//storage module information

/**
 * @name: module name
 * @id: module
 * @parent: this.module have parent
 * @dependencies: this module dependency's permission
 */
class Module {
    private String name;
    private String parent;
    private String id;

    private List<Dependency> dependencies = new ArrayList<>();

    public Module(String name, String parent, String id, List<Dependency> dependencies) {
        this.name = name;
        this.parent = parent;
        this.id = id;
        this.dependencies = dependencies;
    }

    public Module() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }
}

//storage dependency
class Dependency {

    String name;
    String action;

    public Dependency(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public Dependency() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

/**
 * @value: storage [...parent].child
 * @parent: name's parent
 * @name : this is module name
 */
class Parent {
    private String parent;
    private String name;

    private String value;

    public Parent() {
    }


    public Parent(String name) {
        this.name = name;
    }

    public Parent(String name, String parent, String value) {
        this.parent = parent;
        this.name = name;
        this.value = value;
    }

    public String getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


/**
 * @readPath: need update java this.path
 * @writePath: write java file to  this.path
 * @read: permission read information
 * @write: permission write information
 * @dependency: permission dependency
 */
class Information {
    String readPath;
    String writePath;
    String read;
    String write;
    String dependency;

    public Information(String readPath, String writePath, String read, String write, String dependency) {
        this.readPath = readPath;
        this.writePath = writePath;
        this.read = read;
        this.write = write;
        this.dependency = dependency;
    }

    public Information() {
    }
}