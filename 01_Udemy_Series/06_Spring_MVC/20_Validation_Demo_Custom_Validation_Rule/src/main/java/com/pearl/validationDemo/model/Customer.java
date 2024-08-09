package com.pearl.validationDemo.model;

import com.pearl.validationDemo.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    //make firstName is required field
    @NotNull(message = "is Required")
    @Size(min=1, message = "is Required")
    private String firstName = "";

    private String lastName;

    //make freePasses to range validation
    @NotNull(message = "is Required")
    @Min(value = 0, message = "must be grater than or equal to zero")
    @Max(value = 10, message = "must be less than or equal to 10")
    private Integer freePasses;

    //make postalCode validated using regular expression
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    @CourseCode(value = "SE", message = "must start with SE")
    private String courseCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
