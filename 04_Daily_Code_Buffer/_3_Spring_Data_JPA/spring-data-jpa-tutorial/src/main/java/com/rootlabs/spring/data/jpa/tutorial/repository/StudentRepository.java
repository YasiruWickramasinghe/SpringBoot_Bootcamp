package com.rootlabs.spring.data.jpa.tutorial.repository;

import com.rootlabs.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 1) JPA -> There are in build JPA Query Methods

    // 2) JPA Custom Query
        //We can define Custom JPA Names Like below

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


    // 3) JPQL
        // Query language specific to jpa like below
        // select s from Student s where s.emailId = ?1

        //@Query Annotation
        //Create method and Custom JPQL Query for get data
        //this is not Native SQL query
        //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
        //In JPQL Query we write based on the class not focus on database table name or property

        // Find Student By Email Address using JPQL
        @Query("select s from Student s where s.emailId = ?1")
        Student getStudentByEmailAddress(String emailId);

        // Find Student First Name By Email Address using JPQL
        @Query("select s.firstName from Student s where s.emailId = ?1")
        String getStudentFirstNameByEmailAddress(String emailId);

    // 4) Native SQL Query

        // Find Student By Email Address using Native SQL
        @Query(
                value = "SELECT * FROM tbl_student s where s.email_address = ?1",
                nativeQuery = true
        )
        Student getStudentByEmailAddressNativeSQL(String emailId);

    // 5) Query Named Parameter

        // Find Student By Email Address using Naming Parameters
        @Query(
                value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
                nativeQuery = true
        )
        Student getStudentByEmailAddressNamedParam(@Param("emailId") String emailId);

    // 6) Above all queries used for FETCHING data, Here we focus on UPDATE and DELETE data

        // Update Student First Name By EmailId
        @Modifying //indicate that the query will modify the database state, This annotation is essential because, by default, @Query methods are assumed to be read-only.
        @Transactional // ensures that the methods are executed within a transaction. If any of the operations fail (e.g., an exception is thrown), the transaction will be rolled back, maintaining data consistency.
        @Query(
                value = "update tbl_student set first_name = ?1 where email_address = ?2",
                nativeQuery = true
        )
        int updateStudentNameByEmailId(String firstName, String emailId);

}
