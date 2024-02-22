package com.pearl.restcruddemo.dao;

import com.pearl.restcruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
