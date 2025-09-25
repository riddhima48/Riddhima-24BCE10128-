package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private final DataStore ds = DataStore.getInstance();

    public Student addStudent(String id, String regNo, String name, String email){
        if(ds.students().containsKey(id)) throw new IllegalArgumentException("Duplicate student id");
        Student s = new Student(id, regNo, name, email);
        ds.students().put(id, s);
        return s;
    }

    public Optional<Student> findById(String id){ return Optional.ofNullable(ds.students().get(id)); }
    public List<Student> listAll(){ return new ArrayList<>(ds.students().values()); }

    public void deactivate(String id){
        Student s = ds.students().get(id);
        if(s!=null) s.deactivate();
    }

    public List<Student> topStudentsByGPA(int n){
        return ds.students().values().stream()
            .sorted(Comparator.comparingDouble(this::computeGPA).reversed())
            .limit(n).collect(Collectors.toList());
    }

    public double computeGPA(Student s){
        double totalPoints = 0; int totalCredits = 0;
        for(Enrollment e: s.getEnrollments()){
            if(e.getMarks().isPresent()){
                Grade g = e.getGrade();
                totalPoints += g.getPoints() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
            }
        }
        return totalCredits==0?0.0: totalPoints/totalCredits;
    }
}
