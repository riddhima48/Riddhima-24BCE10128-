package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;
import java.util.stream.Collectors;

public class CourseService {
    private final DataStore ds = DataStore.getInstance();

    public Course addCourse(Course c){
        if(ds.courses().containsKey(c.getCode())) throw new IllegalArgumentException("Duplicate course");
        ds.courses().put(c.getCode(), c);
        return c;
    }

    public Optional<Course> find(String code){ return Optional.ofNullable(ds.courses().get(code)); }
    public List<Course> listAll(){ return new ArrayList<>(ds.courses().values()); }

    public List<Course> findByInstructor(String instructor){
        return ds.courses().values().stream().filter(c->c.getInstructor().equalsIgnoreCase(instructor)).collect(Collectors.toList());
    }
}
