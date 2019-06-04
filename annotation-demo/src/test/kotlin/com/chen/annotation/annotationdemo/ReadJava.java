package com.chen.annotation.annotationdemo;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReadJava {
    @Test
    public void readTest() {
        String url = "/home/chen/work/blog_code/java/annotation-demo/src/main/java/com/chen/annotation/annotationdemo/controller/UserController.java";

        write("/home/chen/work/blog_code/java/annotation-demo/src/main/java/com/chen/annotation/annotationdemo/controller/" +
                "UserController9.java", read(url));
    }


    @Test
    public void stringTest() {
        String str = "fffdddee,";
        System.out.println(str.substring(0, str.length() - 1));
    }

    @Test
    public void findParentTest() {
        List<Parent> parents = new ArrayList<>();
//
//        Parent parent1 = new Parent("user", "company");
//        Parent parent2 = new Parent("company", "specialist");
//        Parent parent3 = new Parent("department", "company");
//        Parent parent4 = new Parent("specialist", null);
//        Parent parent5 = new Parent("sub-specialist", "specialist");
//        Parent parent6 = new Parent("label", null);
//        Parent parent7 = new Parent("sub-label", "label");
//        Parent parent8 = new Parent("device", "sub-label");
//        Parent parent9 = new Parent("chanel", "");
//        Parent parent10 = new Parent("sub-chanel", "chanel");
//        Parent parent11 = new Parent("sub-department", "department");
//        Parent parent12 = new Parent("sub-company", "company");
//        Parent parent13 = new Parent("task-defined", "department");
//        Parent parent14 = new Parent("role", "department");
//        Parent parent15 = new Parent("sub-role", "role");
//        Parent parent16 = new Parent("user-role", "sub-role");
//        Parent parent17 = new Parent("sub-user-role", "user-role");
//        Parent parent18 = new Parent("sub-sub-company", "sub-company");
//        Parent parent19 = new Parent("sub-sub-label", "sub-label");
//        Parent parent20 = new Parent("specialist-1", "");
//        Parent parent21 = new Parent("specialist-2", "");
//        Parent parent22 = new Parent("specialist-3", "");
//        Parent parent23 = new Parent("label-1", null);
//        Parent parent24 = new Parent("label-2", "");

//        parents.add(parent1);
//        parents.add(parent2);
//        parents.add(parent3);
//        parents.add(parent4);
//        parents.add(parent5);
//        parents.add(parent6);
//        parents.add(parent7);
//        parents.add(parent8);
//        parents.add(parent9);
//        parents.add(parent10);
//        parents.add(parent11);
//        parents.add(parent12);
//        parents.add(parent13);
//        parents.add(parent14);
//        parents.add(parent15);
//        parents.add(parent16);
//        parents.add(parent17);
//        parents.add(parent18);
//        parents.add(parent19);
//        parents.add(parent20);
//        parents.add(parent21);
//        parents.add(parent22);
//        parents.add(parent23);
//        parents.add(parent24);

        Date start = new Date();

        fixFindParent(parents);
        Date end = new Date();

        System.out.println(end.getTime() - start.getTime());

        parents.forEach(r -> {
            System.out.println(r.toString());
        });

    }

    public void fixFind(List<Parent> parentList, Parent parent) {
        List<Parent> parents = parentList.stream()
                .filter(r -> r.getParent() != null && !r.getParent().equals(""))
                .filter(r -> r.getParent().equals(parent.getName()))
                .collect(Collectors.toList());

        if (parents != null && parents.size() > 0) {
            if (parent.getValue() == null)
                parent.setValue(parent.getName());

            parents.forEach(j -> {

                j.setValue(parent.getValue() + "." + j.getName());
                fixFind(parentList, j);
            });
        }

    }

    int counter = 0;

    public void fixFindParent(List<Parent> parentList) {

        List<Parent> parentNull = parentList.stream()
                .filter(r -> r.getParent() == null || r.getParent().equals(""))
                .collect(Collectors.toList());

        List<Parent> parents = parentList.stream()
                .filter(r -> r.getParent() != null && !r.getParent().equals(""))
                .collect(Collectors.toList());

        for (int j = 0; j < parents.size(); j++) {
            for (int i = 0; i < parentNull.size(); i++) {

                ++ counter;

                Parent p = parents.get(j);

                Parent c = parentNull.get(i);

                if (p.getParent() == c.getName()) {
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

    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;

            String code = "";
            Boolean state = false;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {

                if (tempString.startsWith("public class")) {
                    state = false;
                } else if (tempString.startsWith("@PermissionModule(")) {
                    state = true;
                }

                if (state == true) {
                    code += tempString;
                }


                // 显示行号
                System.out.println("line " + line + ": " + tempString);
            }

            System.out.println(code);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public String read(String filePath) {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();

        try {
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filePath));

            String code = "";
            Boolean state = false;

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {

                if (line.startsWith("public class")) {
                    if (state == true) {
                        buf.append("test");
                    }

                    state = false;
                } else if (line.startsWith("@PermissionModule(")) {
                    state = true;
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

class Parent {
    private String parent;
    private String name;

    private String value;

    public Parent(String name) {
        this.name = name;
    }

    public Parent(String name, String parent) {
        this.parent = parent;
        this.name = name;
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

    @Override
    public String toString() {
        return "Parent{" +
                "parent='" + parent + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
