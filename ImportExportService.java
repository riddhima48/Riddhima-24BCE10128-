package edu.ccrm.io;

import edu.ccrm.domain.*;
import edu.ccrm.service.DataStore;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class ImportExportService {
    private final Path dataFolder = AppPaths.dataFolder();
    private final DataStore ds = DataStore.getInstance();

    public ImportExportService(){ try { Files.createDirectories(dataFolder); } catch(Exception e){} }

    public void importStudents(Path csv) throws IOException {
        try(Stream<String> lines = Files.lines(csv)){
            lines.skip(1).map(l->l.split(",")).forEach(parts->{
                if(parts.length>=4){
                    String id = parts[0].trim();
                    String reg = parts[1].trim();
                    String name = parts[2].trim();
                    String email = parts[3].trim();
                    ds.students().putIfAbsent(id, new Student(id, reg, name, email));
                }
            });
        }
    }

    public void importCourses(Path csv) throws IOException {
        try(Stream<String> lines = Files.lines(csv)){
            lines.skip(1).map(l->l.split(",")).forEach(parts->{
                if(parts.length>=5){
                    String code = parts[0].trim();
                    Course c = new Course.Builder(code)
                        .title(parts[1].trim())
                        .credits(Integer.parseInt(parts[2].trim()))
                        .instructor(parts[3].trim())
                        .department(parts[4].trim())
                        .build();
                    ds.courses().putIfAbsent(code, c);
                }
            });
        }
    }

    public void exportAll(Path outStudents, Path outCourses) throws IOException {
        List<String> sLines = new ArrayList<>();
        sLines.add("id,regNo,name,email");
        ds.students().values().forEach(s -> sLines.add(String.join(",", s.getId(), s.getRegNo(), s.getFullName(), s.getEmail())));
        Files.write(outStudents, sLines);

        List<String> cLines = new ArrayList<>();
        cLines.add("code,title,credits,instructor,department");
        ds.courses().values().forEach(c -> cLines.add(String.join(",", c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getInstructor(), c.getDepartment())));
        Files.write(outCourses, cLines);
    }
}
