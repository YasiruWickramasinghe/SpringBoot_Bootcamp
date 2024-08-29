package com.rootlabs.Springboot.tutorial.service;

import com.rootlabs.Springboot.tutorial.entity.Department;

import java.util.List;

public interface DepartmentService {

    //Save Department
    public Department saveDepartment(Department department);

    //Get List of All Departments
    public List<Department> fetchDepartmentList();

    //Get Department By Id
    public Department fetchDepartmentById(Long departmentId);

    //Update Department By ID
    public Department updateDepartment(Long departmentId, Department department);

    //Delete Department By ID
    public void deleteDepartmentById(Long departmentId);

    //Get Department By Name
    public Department fetchDepartmentByName(String departmentName);
}
