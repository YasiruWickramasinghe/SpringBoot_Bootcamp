package com.rootlabs.Springboot.tutorial.service;

import com.rootlabs.Springboot.tutorial.entity.Department;
import com.rootlabs.Springboot.tutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    //Save Department
    public Department saveDepartment(Department department);

    //Get List of All Departments
    public List<Department> fetchDepartmentList();

    //Get Department By Id
    //Throw Exception if Department Not Exist
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    //Update Department By ID
    public Department updateDepartment(Long departmentId, Department department);

    //Delete Department By ID
    public void deleteDepartmentById(Long departmentId);

    //Get Department By Name
    public Department fetchDepartmentByName(String departmentName);
}
