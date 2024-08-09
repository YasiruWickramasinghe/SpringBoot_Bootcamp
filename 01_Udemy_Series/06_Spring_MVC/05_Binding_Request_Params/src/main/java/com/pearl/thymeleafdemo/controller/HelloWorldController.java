package com.pearl.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    //need a controller method to show initial HTML form
    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
    // 1) using html -> Student name: <span th:text="${param.studentName}"/>
    @RequestMapping("/processForm")
    public String processFormV1(){
        return "helloworld";
    }

    // need a controller method to read form data and
    // 2) add data to the model using HttpServletRequest method

    @RequestMapping("/processFormV2")
    public String processFormV2(HttpServletRequest request, Model model){

        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");

        // convert the dat to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return  "helloworld";
    }

    // need a controller method to read form data and
    // 3) add data to the model using @RequestParam Annotation - Recommended way for Spring MVC
    @RequestMapping("/processFormV3")
    public String processFormV3(@RequestParam("studentName") String theName, Model model){

        // convert the dat to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey my friend from V3! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return  "helloworld";
    }

}
