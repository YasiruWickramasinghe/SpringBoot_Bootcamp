package com.pearl.springboot.thymeleafdemo.controller;

import com.pearl.springboot.thymeleafdemo.entity.Employee;
import com.pearl.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // showing the List Table
    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // get the employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    // Show Form For Add new Employee
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        // add attribute to the model
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    // Show form for update an Existing Employee
    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee in the model to pre populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "employees/employee-form";
    }

    // Save Employee Data to Database ( Insert/Update )
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        // save the employee
        employeeService.save(theEmployee);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to the /employee/list
        return "redirect:/employees/list";
    }
}
