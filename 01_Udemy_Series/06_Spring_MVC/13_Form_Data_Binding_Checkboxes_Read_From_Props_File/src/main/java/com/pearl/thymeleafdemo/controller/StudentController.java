package com.pearl.thymeleafdemo.controller;

import com.pearl.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    // Inject the countries data from property file
    @Value("${countries}")
    private List<String> countries;

    // Inject the language data from property file
    @Value("${languages}")
    private List<String> languages;

    // Inject the Opearting Systems data from property file
    @Value("${systems}")
    private List<String> systems;

    // create method for showing form and add to the model

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel){

        // create new student object
        Student theStudent = new Student();

        // add student object to the model
        theModel.addAttribute("student", theStudent);

        // add the list of countries to the model
        theModel.addAttribute("countries", countries);

        // add the list of languages to the model
        theModel.addAttribute("languages", languages);

        // add the list of Operating systems to the model
        theModel.addAttribute("systems", systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent){

        // log the input data
        System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());

        return "student-confirmation";
    }
}
