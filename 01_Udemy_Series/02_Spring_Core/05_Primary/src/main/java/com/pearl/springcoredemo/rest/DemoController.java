package com.pearl.springcoredemo.rest;


import com.pearl.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define a private field for the dependency
    private Coach myCoach;

    //define a constructor for dependency injection
    //here also have @primary coach but @Qualifiers have more priority
        //    public DemoController(@Qualifier("trackCoach") Coach theCoach){
        //        myCoach = theCoach;
        //    }
    @Autowired
    public DemoController(Coach theCoach){
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
