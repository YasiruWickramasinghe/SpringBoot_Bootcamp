package com.rootlabs.Springboot.tutorial.controller;

import com.rootlabs.Springboot.tutorial.entity.Department;
import com.rootlabs.Springboot.tutorial.error.DepartmentNotFoundException;
import com.rootlabs.Springboot.tutorial.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    //Inject DepartmentService
    @Autowired
    private DepartmentService departmentService;

    //Enable Loggers for Debugging Errors
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    //Save Department
    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        //Use Logger
        LOGGER.info("Inside saveDepartment of DepartmentController");

        return departmentService.saveDepartment(department);
    }

    //Get List of All Departments
    @GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        //Use Logger
        LOGGER.info("Inside fetchDepartment of DepartmentController");
        return departmentService.fetchDepartmentList();
    }

    //Get Department By Id
    //Throw Exception if Department Not Exist
    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    //Update Department By ID
    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return departmentService.updateDepartment(departmentId,department);
    }

    //Delete Department By ID
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department Deleted Successfully!";
    }

    //Get Department By Name
    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.fetchDepartmentByName(departmentName);
    }

}
