package com.pearl.springboot.thymeleafdemo.dao;

import com.pearl.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // add a method to Sort by first name
    public List<Employee> findAllByOrderByFirstNameAsc();


}
