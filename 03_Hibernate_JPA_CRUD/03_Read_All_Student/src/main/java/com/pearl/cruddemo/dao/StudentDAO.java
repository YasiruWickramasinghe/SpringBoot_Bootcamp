package com.pearl.cruddemo.dao;

import com.pearl.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);
    Student findById(Integer id);

    List<Student> findAll();
}
