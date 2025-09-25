package edu.ccrm.domain;

public class Instructor extends Person {
    private String dept;

    public Instructor(String id, String fullName, String email, String dept) {
        super(id, fullName, email);
        this.dept = dept;
    }

    public String getDept() { return dept; }

    @Override
    public String profile() {
        return String.format("Instructor: %s (%s)\nDept: %s\nEmail: %s\n", fullName, id, dept, email);
    }
}
