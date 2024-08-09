package com.pearl.restcruddemo.service;

import com.pearl.restcruddemo.dao.EmployeeDAO;
import com.pearl.restcruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private EmployeeDAO employeeDAO;

    //Inject the EmployeeDAO
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }

    // Get All Employee List
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    // Find Employee by Id
    @Override
    public Employee findById(int theId) {
        return employeeDAO.findById(theId);
    }

    //Create and Update Employee
    @Transactional //add transactional setup here service
    @Override
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    // Delete Employee by Id
    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);
    }
}
