package com.rootlabs.Springboot.tutorial.service;

import com.rootlabs.Springboot.tutorial.entity.Department;
import com.rootlabs.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    //Inject DepartmentService for Testing
    @Autowired
    private DepartmentService departmentService;

    //Mock the RepositoryService
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        //Use Mocked Repository
        Department department =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Matara")
                        .departmentCode("IT-05")
                        .departmentId(1L)
                        .build();

        //pass above mock object to service layer and  used to set up the mock's behavior, ensuring proper isolation.
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }

    // Here Testing Service Layer Methods
    //Write Test Case For -> Fetch Department By Name
    @Test
    //Give name to Test
    @DisplayName("Get Data based on Valid Department Name")
    //Disable only this particular test
    //@Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound() {
        //The test case verifies that the fetchDepartmentByName method returns the correct Department object when a valid department name is provided.
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        //assertion is used to validate that the returned department's name matches the expected value.
        assertEquals(departmentName, found.getDepartmentName());
    }
}