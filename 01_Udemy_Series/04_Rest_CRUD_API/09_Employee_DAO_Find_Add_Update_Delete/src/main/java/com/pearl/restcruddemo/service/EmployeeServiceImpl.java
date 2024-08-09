package com.pearl.restcruddemo.service;

import com.pearl.restcruddemo.dao.EmployeeDAO;
import com.pearl.restcruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private EmployeeDAO employeeDAO;

    //Inject the EmployeeDAO
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}
