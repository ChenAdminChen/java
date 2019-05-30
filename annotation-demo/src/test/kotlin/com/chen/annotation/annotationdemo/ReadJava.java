package com.chen.annotation.annotationdemo;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

        Parent parent1 = new Parent("user", "company");
        Parent parent2 = new Parent("company", "specialist");
        Parent parent3 = new Parent("department", "company");
        Parent parent4 = new Parent("specialist", "");

        parents.add(parent1);
        parents.add(parent2);
        parents.add(parent3);
        parents.add(parent4);

        List<Parent> list = new ArrayList<>();

        for (int i = 0; i < parents.size(); i++) {

            list.add(findParent(parents, parents.get(i)));
        }

        list.stream().forEach(r -> System.out.println(r.toString()));
    }


    //find parent
    public Parent findParent(List<Parent> parents, Parent parent) {

        if (parent.getParent() == null || parent.getParent().equals("")) {
            System.out.println(parents.toString());
            return parent;
        }

        parents.stream()
                .filter(r -> r.getName() == parent.getParent())
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

        return findParent(parents, parent);
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

    public Parent(String parent, String name, String value) {
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

    @Override
    public String toString() {
        return "Parent{" +
                "parent='" + parent + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
