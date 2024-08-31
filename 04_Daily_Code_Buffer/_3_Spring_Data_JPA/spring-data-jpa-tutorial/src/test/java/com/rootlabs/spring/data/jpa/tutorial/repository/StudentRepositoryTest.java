package com.rootlabs.spring.data.jpa.tutorial.repository;

import com.rootlabs.spring.data.jpa.tutorial.entity.Guardian;
import com.rootlabs.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// normally use test our repository class using @DataJpaTest but,
// Here Didn't use below Annotation because we didn't create serviceImpl class
//@DataJpaTest
class StudentRepositoryTest {

    //Inject Student Repository
    @Autowired
    private StudentRepository studentRepository;

    //Enable Loggers for Debugging Errors
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryTest.class);

    // 1) JPA

        /*
        // Test Case for Save Student
        @Test
        public void saveStudent(){
            //Use Entity Class object  Builder Design Pattern here
            Student student = Student.builder()
                    .emailId("yasiru@gmail.com")
                    .firstName("Yasiru")
                    .lastName("Wickramasinghe")
                    .guardianName("Jayaweera")
                    .guardianEmail("jayaweera@gmail.com")
                    .guardianMobile("0771234567")
                    .build();

            //save student using jpa repository save function
            studentRepository.save(student);
        }
        */

        // Test Case for save student with guardian details
        @Test
        public void saveStudentWithGuardian(){

            //create guardian obj
            Guardian guardian = Guardian.builder()
                    .email("sarath@gmail.com")
                    .name("Sarath")
                    .mobile("0771234567")
                    .build();

            //create student obj
            Student student = Student.builder()
                    .emailId("muthu@gmail.com")
                    .firstName("Muthu")
                    .lastName("Wijenayake")
                    .guardian(guardian)
                    .build();

            //save student using jpa repository save function
            studentRepository.save(student);
        }

        // Test Case for Get All Student
        @Test
        public void printAllStudent(){
            List<Student> studentList = studentRepository.findAll();

            //print all student
            System.out.println("StudentList = " + studentList);
        }

    // 2) JPA Custom

        // Test Case for Custom Repository method called "findByFirstName"
        @Test
        public void printStudentByFirstName(){
            List<Student> students = studentRepository.findByFirstName("Muthu");

            //print student
            System.out.println("student = " + students);
        }

        // Test Case for Custom Repository method called "findByFirstNameContaining"
        @Test
        public void printStudentByFirstNameContaining(){
            List<Student> students = studentRepository.findByFirstNameContaining("u");

            //print student
            System.out.println("student = " + students);
        }

        // Test Case for Custom Repository method called "findByLastNameNotNull"


        // Test Case for Custom Repository method called "findByGuardianName"
        @Test
        public void printStudentBasedOnGuardianName(){
            List<Student> students = studentRepository.findByGuardianName("Sarath");

            System.out.println("students = " + students);
        }

    // 3) Using JPQL

        // Test Case for Find Student By Email Address using JPQL
        @Test
        public void PrintStudentByEmailAddress(){
            Student student = studentRepository.getStudentByEmailAddress("yasiru@gmail.com");

            LOGGER.info("Student = {}", student);
        }

        // Test Case for Find Student First Name By Email Address using JPQL
        @Test
        public void PrintStudentFirstNameByEmailAddress(){
            String firstName = studentRepository.getStudentFirstNameByEmailAddress("muthu@gmail.com");

            LOGGER.info("Student First Name = {}", firstName );
        }

    // 4) Using Native SQL

        // Test Case for Find Student By Email Address using Native SQL
        @Test
        public void PrintStudentByEmailAddressNativeSQL(){
            Student student = studentRepository.getStudentByEmailAddressNativeSQL("muthu@gmail.com");

            LOGGER.info("Student = {}", student);
        }

    // 5) Using Named Parameter

        // Test Case for Find Student By Email Address using Named Parameter
        @Test
        public void PrintStudentByEmailAddressNamedParam(){
            Student student = studentRepository.getStudentByEmailAddressNamedParam("yasiru@gmail.com");

            LOGGER.info("Student = {}", student);
        }

    // 6) Above all queries used for FETCHING data, Here we focus on UPDATE and DELETE data

        // Update Student First Name By EmailId
        @Test
        public void updateStudentNameByEmailId(){
            studentRepository.updateStudentNameByEmailId(
                    "Yash",
                    "yasiru@gmail.com"
            );
        }

}