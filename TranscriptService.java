package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.stream.Collectors;

public class TranscriptService {
    private final StudentService ss = new StudentService();

    public String transcript(Student s){
        StringBuilder sb = new StringBuilder();
        sb.append("==== Transcript ====\n");
        sb.append(s.profile()).append("\n");
        sb.append("GPA: ").append(String.format("%.2f", ss.computeGPA(s))).append("\n");
        sb.append("Grades:\n");
        for(Enrollment e: s.getEnrollments()){
            sb.append(String.format("%s - %s : %s (%s)\n", e.getCourse().getCode(), e.getCourse().getTitle(),
                e.getMarks().isPresent()?e.getMarks().get():"N/A", e.getGrade()==null?"-":e.getGrade()));
        }
        return sb.toString();
    }
}
