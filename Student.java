package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.*;

public class Student extends Person {
    public enum Status { ACTIVE, INACTIVE }

    private String regNo;
    private Status status = Status.ACTIVE;
    private final Map<String, Enrollment> enrollments = new LinkedHashMap<>();

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
    }

    public String getRegNo() { return regNo; }
    public Status getStatus() { return status; }
    public void deactivate() { this.status = Status.INACTIVE; }

    public void enroll(Enrollment e) { enrollments.put(e.getCourse().getCode(), e); }
    public void unenroll(String courseCode) { enrollments.remove(courseCode); }
    public Collection<Enrollment> getEnrollments() { return enrollments.values(); }

    @Override
    public String profile() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: ").append(fullName).append(" ("+regNo+")\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Enrollments:\n");
        for (Enrollment e: enrollments.values()) {
            sb.append("  ").append(e.simpleString()).append("\n");
        }
        return sb.toString();
    }
}
