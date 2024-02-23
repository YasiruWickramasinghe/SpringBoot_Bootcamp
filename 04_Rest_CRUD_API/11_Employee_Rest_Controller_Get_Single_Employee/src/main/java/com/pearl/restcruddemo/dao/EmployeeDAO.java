package com.pearl.restcruddemo.dao;

import com.pearl.restcruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    //Get all Employee List
    List<Employee> findAll();

    //Find Employee by Id
    Employee findById(int theId);

    //Create or Update Employee
    Employee save(Employee theEmployee);

    //Delete an Employee by Id
    void deleteById(int theId);
}