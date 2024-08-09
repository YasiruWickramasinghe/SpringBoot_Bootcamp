package com.pearl.restcruddemo.service;

import com.pearl.restcruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
}
