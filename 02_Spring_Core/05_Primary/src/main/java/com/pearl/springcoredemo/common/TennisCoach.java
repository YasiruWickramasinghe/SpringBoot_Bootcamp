package com.pearl.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//here define tennisCoach as primary if not specify any coach then choose this one
@Primary
public class TennisCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley.";
    }
}
