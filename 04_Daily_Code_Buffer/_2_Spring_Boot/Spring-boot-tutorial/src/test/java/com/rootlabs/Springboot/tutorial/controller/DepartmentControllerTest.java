package com.rootlabs.Springboot.tutorial.controller;

import com.rootlabs.Springboot.tutorial.entity.Department;
import com.rootlabs.Springboot.tutorial.error.DepartmentNotFoundException;
import com.rootlabs.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    // Inject MockMVC
    @Autowired
    private MockMvc mockMvc;

    //Mock Service Layer
    // racecourse in controller layer we call to the service layer
    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        //this is Output Object
        //which mean user fetched data
        department = Department.builder()
                .departmentAddress("Kandy")
                .departmentCode("IS-05")
                .departmentName("IS")
                .departmentId(1L)
                .build();
    }

    //Test Case for SaveDepartment method
    @Test
    void saveDepartment() throws Exception {
        //this is Input object
        //which mean user input data
        Department inputDepartment = Department.builder()
                .departmentAddress("Kandy")
                .departmentCode("IT-05")
                .departmentName("IT")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        //call the endpoint to perform post operation
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"IS\",\n" +
                        "\t\"departmentAddress\":\"Kandy\",\n" +
                        "\t\"departmentCode\":\"IS-05\"\n" +
                        "}")).andExpect(status().isOk());

    }

    //Test Case fo fetchDepartmentById
    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        //call the endpoint to perform get operation
        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));
    }
}