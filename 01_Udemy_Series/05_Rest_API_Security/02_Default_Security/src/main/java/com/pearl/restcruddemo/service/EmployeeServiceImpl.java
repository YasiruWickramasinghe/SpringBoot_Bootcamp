package com.pearl.restcruddemo.service;

import com.pearl.restcruddemo.dao.EmployeeRepository;
import com.pearl.restcruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepositoty;

    //Inject the EmployeeDAO
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepositoty = theEmployeeRepository;
    }

    // Get All Employee List
    @Override
    public List<Employee> findAll() {
        return employeeRepositoty.findAll();
    }

    // Find Employee by Id
    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepositoty.findById(theId);

        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // did not find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    //Create and Update Employee
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepositoty.save(theEmployee);
    }

    // Delete Employee by Id
    @Override
    public void deleteById(int theId) {
        employeeRepositoty.deleteById(theId);
    }
}
