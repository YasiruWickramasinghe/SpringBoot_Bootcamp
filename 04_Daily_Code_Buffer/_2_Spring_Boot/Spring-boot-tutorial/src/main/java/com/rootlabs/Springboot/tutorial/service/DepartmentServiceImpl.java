package com.rootlabs.Springboot.tutorial.service;

import com.rootlabs.Springboot.tutorial.entity.Department;
import com.rootlabs.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    //Inject DepartmentRepository
    @Autowired
    private DepartmentRepository departmentRepository;

    //Save Department
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    //Get List of All Departments
    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    //Get Department By Id
    @Override
    public Department fetchDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }

    //Update Department By ID
    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        //Get particular department
        Department depDB = departmentRepository.findById(departmentId).get();

        //Check departmentName notNull and notBlank in fetched property
        if(Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName())) {
                depDB.setDepartmentName(department.getDepartmentName());
        }

        //Check departmentCode notNull and notBlank in fetched property
        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())) {
            depDB.setDepartmentCode(department.getDepartmentCode());
        }

        //Check departmentAddress notNull and notBlank in fetched property
        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        //Check departmentName notNull and notBlank in fetched property
        if(Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName())) {
            depDB.setDepartmentName(department.getDepartmentName());
        }

        return departmentRepository.save(depDB);
    }

    //Delete Department By ID
    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    //Get Department By Name
    //To fetching Data by name JPA has not available default method therefore we need to create one
    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }


}
