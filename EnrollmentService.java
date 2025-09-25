package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class EnrollmentService {
    private final DataStore ds = DataStore.getInstance();
    private final StudentService ss = new StudentService();

    public void enroll(String studentId, String courseCode){
        Student s = ds.students().get(studentId);
        Course c = ds.courses().get(courseCode);
        if(s==null) throw new IllegalArgumentException("Student not found");
        if(c==null) throw new IllegalArgumentException("Course not found");
        Enrollment e = new Enrollment(s, c);
        s.enroll(e);
    }

    public void recordMarks(String studentId, String courseCode, double marks){
        Student s = ds.students().get(studentId);
        if(s==null) throw new IllegalArgumentException("Student not found");
        Enrollment e = s.getEnrollments().stream().filter(en->en.getCourse().getCode().equals(courseCode)).findFirst().orElse(null);
        if(e==null) throw new IllegalArgumentException("Enrollment not found");
        e.setMarks(marks);
    }

    public void unenroll(String studentId, String courseCode){
        Student s = ds.students().get(studentId);
        if(s!=null) s.unenroll(courseCode);
    }
}
