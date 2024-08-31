package com.rootlabs.spring.data.jpa.tutorial.repository;

import com.rootlabs.spring.data.jpa.tutorial.entity.Course;
import com.rootlabs.spring.data.jpa.tutorial.entity.Student;
import com.rootlabs.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    // Get All Courses and also Course Materials
    @Test
    public void printCoursesAndCourseMaterials(){
        List<Course> courses = courseRepository.findAll();

        System.out.println("courses = " + courses);
    }

    //save Course With Teacher - many to One uni-direction relationship
    @Test
    public void saveCourseWithTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Shankila")
                .lastName("Wijenayake")
                .build();

        Course course = Course.builder()
                .courseTitle("Python")
                .credits(4)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    //Pagination and Sorting

        // Find All Pagination
        @Test
        public void findAllPagination(){
            Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
            Pageable secondPageWithTwoRecords = PageRequest.of(1,2);

            // 1) Get Page Contents
            List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords)
                    .getContent();

            // 2) Get Total Elements
            long totalElements = courseRepository.findAll(secondPageWithTwoRecords)
                    .getTotalElements();

            // 3) Get Total Pages
            long totalPages = courseRepository.findAll(secondPageWithTwoRecords)
                    .getTotalPages();

            System.out.println("courses = " + courses);
            System.out.println("total pages = " + totalPages);
            System.out.println("total elements = " + totalElements);

        }

        // Find All Sorting
        @Test
        public void findAllSorting(){

            // Sort By Course Title
            Pageable sortByCourseTitle = PageRequest.of(
                    0,
                    2,
                    Sort.by("courseTitle")
            );

            // Sort By Credit Desc
            Pageable sortByCreditDesc = PageRequest.of(
                    0,
                    2,
                    Sort.by("credit").descending()
            );

            // Sort By Title And Credit Desc
            Pageable sortByTitleAndCreditDesc = PageRequest.of(
                    0,
                    2,
                    Sort.by("courseTitle")
                            .descending()
                            .and(Sort.by("credit"))
            );

            List<Course> courses = courseRepository.findAll(sortByCourseTitle)
                    .getContent();

            System.out.println("courses = " + courses);
        }

       // Custom Soring
        @Test
        public void printFindByCourseTitleContaining(){
            Pageable firstPageTenRecords =
                    PageRequest.of(0,10);

            List<Course> courses = courseRepository.findByCourseTitleContaining(
                    "D",
                    firstPageTenRecords).getContent();

            System.out.println("course = " + courses);
        }

    // Test Case for many to Many Relationship
    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Tiroma")
                .lastName("Oshani")
                .build();

        Student student = Student.builder()
                .firstName("Thisaru")
                .lastName("Sajeewa")
                .emailId("thisaru@gmail.com")
                .build();

        Course course = Course.builder()
                .courseTitle("AI")
                .credits(10)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }

}