package com.rootlabs.spring.data.jpa.tutorial.repository;

import com.rootlabs.spring.data.jpa.tutorial.entity.Course;
import com.rootlabs.spring.data.jpa.tutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    // Save data to Course table as well as course material table
    @Test
    public void SaveCourseMaterial(){
        Course course = Course.builder()
                .courseTitle("Java")
                .credits(6)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.rootlabs.com")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }

    //Get All Course Materials
    @Test
    public void printAllCoursesMaterials(){
        List<CourseMaterial> courseMaterials = repository.findAll();

        System.out.println("courseMaterials = " + courseMaterials);
    }
}