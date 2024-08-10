package com.pearl.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form
    // only support for get request
    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // need a controller method to read form data and
    // only support for post request
    @PostMapping("/processFormV3")
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
