public class Teacher extends Employee {
    private String name;

    public Teacher() {
        super("");
    }

    public Teacher(String email, String name) {
        this(email);

        this.name = name;
    }

    public Teacher(String email) {
        super(email);
    }
}
