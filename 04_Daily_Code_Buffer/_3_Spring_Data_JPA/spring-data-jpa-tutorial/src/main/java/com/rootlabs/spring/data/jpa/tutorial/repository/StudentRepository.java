package com.rootlabs.spring.data.jpa.tutorial.repository;

import com.rootlabs.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find Student By First Name
    List<Student> findByFirstName(String firstName);

    // Find Students By Name containing some letter
    List<Student> findByFirstNameContaining(String name);

    // Find Student that Last Name is Not Null
    List<Student> findByLastNameNotNull();

    // Find Student By Guardian Name
    List<Student> findByGuardianName(String guardianName);

    // Find Student By First Name and Last Name
    Student findByFirstNameAndLastName(String firstName, String lastName);




}
