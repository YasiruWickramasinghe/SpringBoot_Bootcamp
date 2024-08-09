package com.pearl.restcrudapi.rest;

import com.pearl.restcrudapi.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @postConstruct to load the student data... only one
    @PostConstruct
    public void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Muthu", "Wijenayake"));
        theStudents.add(new Student("Yash", "Wickramasinghe"));
        theStudents.add(new Student("Nanda", "Palihakkara"));

    }

    //Define endpoint foe "/students" - return list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }
}
