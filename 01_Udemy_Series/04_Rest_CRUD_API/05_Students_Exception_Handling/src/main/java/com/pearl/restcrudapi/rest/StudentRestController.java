package com.pearl.restcrudapi.rest;

import com.pearl.restcrudapi.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @postConstruct to load the student data... only one
    @PostConstruct
    public void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Muthu", "Wijenayake"));
        theStudents.add(new Student("Yash", "Wickramasinghe"));
        theStudents.add(new Student("Nanda", "Palihakkara"));

    }

    //Define endpoint foe "/students" - return list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    //define endpoint for "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getSingleStudent(@PathVariable int studentId){

        //just index into the list

        //Check the studentId again list size
        if((studentId >= theStudents.size() || studentId < 0)){
            throw new StudentNotFoundException("Student id not fount - " + studentId);
        }

        return theStudents.get(studentId);
    }

    //Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){

        //create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //add another exception handler to catch aby exception (catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){

        //create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
