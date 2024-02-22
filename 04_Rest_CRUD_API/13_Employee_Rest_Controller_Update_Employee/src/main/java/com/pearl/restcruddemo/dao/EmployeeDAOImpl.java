package com.pearl.restcruddemo.dao;

import com.pearl.restcruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    //define field for entity manager
    private EntityManager entityManager;

    //setup constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    // Get All Employee List
    @Override
    public List<Employee> findAll() {

        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee", Employee.class);

        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        //return the result
        return employees;
    }

    // Find Employee by Id
    @Override
    public Employee findById(int theId) {

        // get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // return employee
        return theEmployee;
    }

    //Create and Update Employee
    @Override
    public Employee save(Employee theEmployee) {

        // save employee
        // here merge method perform both create and update depending on id; if id is 0 then create new one else update based on id
        Employee dbEmployee = entityManager.merge(theEmployee);

        //return the dbEmployee
        return dbEmployee;
    }

    // Delete Employee by Id
    @Override
    public void deleteById(int theId) {

        // find employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // remove employee
        entityManager.remove(theEmployee);
    }
}
