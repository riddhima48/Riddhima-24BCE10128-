package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.Optional;

public class Enrollment {
    private final Student student;
    private final Course course;
    private final LocalDate enrolledAt;
    private Double marks; // nullable

    public Enrollment(Student s, Course c) {
        this.student = s; this.course = c; this.enrolledAt = LocalDate.now();
    }

    public Course getCourse(){ return course; }
    public Student getStudent(){ return student; }
    public LocalDate getEnrolledAt(){ return enrolledAt; }

    public void setMarks(Double marks){ this.marks = marks; }
    public Optional<Double> getMarks(){ return Optional.ofNullable(marks); }
    public Grade getGrade(){ return marks == null ? null : Grade.fromMarks(marks); }

    public String simpleString(){
        return String.format("%s [%s] - marks: %s", course.getCode(), course.getTitle(), marks==null?"N/A":marks);
    }
}
