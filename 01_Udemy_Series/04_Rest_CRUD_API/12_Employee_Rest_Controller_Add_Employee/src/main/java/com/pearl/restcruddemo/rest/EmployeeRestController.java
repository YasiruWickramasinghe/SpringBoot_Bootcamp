package com.pearl.restcruddemo.rest;

import com.pearl.restcruddemo.entity.Employee;
import com.pearl.restcruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //Inject EmployeeService (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // Expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // Add mapping for GET "/employees/{employeeId}"
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        // find employee id
        Employee theEmployee = employeeService.findById(employeeId);

        // check is employee null
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        // return the employee
        return theEmployee;
    }

    // Add mapping for POST "/employee" - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        // also just in case they pass an id in JSON... set id to 0
        // this is to force a save of new item... instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }
}
