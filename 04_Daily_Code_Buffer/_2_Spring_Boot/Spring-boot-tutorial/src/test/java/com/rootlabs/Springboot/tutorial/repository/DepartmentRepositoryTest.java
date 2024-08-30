package com.rootlabs.Springboot.tutorial.repository;

import com.rootlabs.Springboot.tutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    //Inject DepartmentRepository for Testing
    @Autowired
    private DepartmentRepository departmentRepository;

    //Inject  Test Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        //create mock object in entity manager
        Department department =
                Department.builder()
                        .departmentName("Mechanical Engineering")
                        .departmentCode("ME-011")
                        .departmentAddress("Colombo")
                        .build();

        //pass above mock object and temporarily save data
        entityManager.persist(department);
    }

    @Test
    public void whenFindById_ThenReturnDepartment(){
        Department department = departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(), "Mechanical Engineering");
    }
}