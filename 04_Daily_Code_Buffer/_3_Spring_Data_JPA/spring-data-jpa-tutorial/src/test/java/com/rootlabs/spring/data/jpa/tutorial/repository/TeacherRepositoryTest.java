package com.rootlabs.spring.data.jpa.tutorial.repository;

import com.rootlabs.spring.data.jpa.tutorial.entity.Course;
import com.rootlabs.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    //Save Teacher
    @Test
    public void saveTeacher(){

        //create many courses and save
        Course courseSE = Course.builder()
                .courseTitle("SE")
                .credits(5)
                .build();

        Course courseIT = Course.builder()
                .courseTitle("IT")
                .credits(3)
                .build();

        Course courseDS = Course.builder()
                .courseTitle("DS")
                .credits(7)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Chathuri")
                .lastName("Vidanarachchi")
                //.courses(List.of(courseSE, courseIT, courseDS)) // achieve one teacher to many courses
                .build();

        teacherRepository.save(teacher);
    }
}