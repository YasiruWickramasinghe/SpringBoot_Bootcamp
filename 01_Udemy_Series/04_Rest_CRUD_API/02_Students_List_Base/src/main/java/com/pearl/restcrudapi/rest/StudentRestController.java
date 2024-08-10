package com.pearl.restcrudapi.rest;

import com.pearl.restcrudapi.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    //Define endpoint foe "/students" - return list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        List<Student> thestudents = new ArrayList<>();

        thestudents.add(new Student("Muthu", "Wijenayake"));
        thestudents.add(new Student("Yash", "Wickramasinghe"));
        thestudents.add(new Student("Nanda", "Palihakkara"));

        return thestudents;
    }

}
