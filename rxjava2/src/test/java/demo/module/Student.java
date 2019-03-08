package demo.module;


import lombok.Data;

@Data
public class Student implements Comparable<Student> {
    private String name;
    private Integer age;
    private Integer sex;

    public Student() {
    }

    public Student(String name, Integer age, Integer sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public int compareTo(Student s) {
        int result = age.compareTo(s.age);

        if (result == 0) {
            return sex.compareTo(s.sex);
        }
        return result;
    }
}
