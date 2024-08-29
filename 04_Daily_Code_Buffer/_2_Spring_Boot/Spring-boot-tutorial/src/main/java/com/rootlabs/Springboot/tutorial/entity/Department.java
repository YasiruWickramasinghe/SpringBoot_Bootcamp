package com.rootlabs.Springboot.tutorial.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    //Add Validation to fields
    @NotBlank(message = "Please Provide Department Name")
    /*
        //Check length of String Field
        @Length(max =5, min = 1)

        //Check Number of element in the array or collection
        @Size(max = 10, min = 0)

        //Validate Email Address
        @Email

        //Check Positive value
        @Positive

        //Check Negative value
        @Negative

        //Check Positive or equal to Zero
        @PositiveOrZero

        //Check Negative or equal to Zero
        @NegativeOrZero

        //Check Future Date
        @Future

        //Check Future Date or Today
        @FutureOrPresent

        //Check Past Date
        @Past

        //Check Past date or Today
        @PastOrPresent
    */

    private String departmentName;
    private String departmentAddress;
    private String departmentCode;

    public Department() {
    }

    public Department(Long departmentId, String departmentName, String departmentAddress, String departmentCode) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentAddress = departmentAddress;
        this.departmentCode = departmentCode;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentAddress='" + departmentAddress + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                '}';
    }
}
