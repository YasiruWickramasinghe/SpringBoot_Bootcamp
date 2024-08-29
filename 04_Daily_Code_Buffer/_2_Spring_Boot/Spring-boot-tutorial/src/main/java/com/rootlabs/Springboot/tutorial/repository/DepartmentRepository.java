package com.rootlabs.Springboot.tutorial.repository;

import com.rootlabs.Springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //Define Method For Fetch data By Department Name
        //public Department findByDepartmentName(String departmentName);

    //Ignore case-sensitive
    public Department findByDepartmentNameIgnoreCase(String departmentName);
}
