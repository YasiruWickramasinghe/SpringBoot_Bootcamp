package com.pearl.cruddemo;

import com.pearl.cruddemo.dao.StudentDAO;
import com.pearl.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

		return runner -> {
			//createStudent(studentDAO);

			//createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//QuaryForStudents(studentDAO);

			//QuaryForStudentsByLastName(studentDAO);

			updateStudent(studentDAO);
		};
	}

	private void updateStudent(StudentDAO studentDAO) {

		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		//change first name to "Scooby"
		System.out.println("Updating student...");
		myStudent.setFirstName("Muthu");

		//update the student
		studentDAO.update(myStudent);

		//display the updated student
		System.out.println("Updated student: " + myStudent);
	}

	/*
	private void QuaryForStudentsByLastName(StudentDAO studentDAO) {

		//get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Jayasinghe");

		//display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}
	*/

	/*
	private void QuaryForStudents(StudentDAO studentDAO) {

		//get a list of students
		List<Student> theStudents = studentDAO.findAll();

		//display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}
	*/

	/*
	private void readStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("kamindu", "vidanarachchi", "yasiru@gmnail.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated is:" + theId);

		//retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("Found the student: " + myStudent);
	}
	*/

	/*
	private void createMultipleStudents(StudentDAO studentDAO) {

		//create multiple students
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("Muthu", "Wijenayake", "muthu@gmnail.com");
		Student tempStudent2 = new Student("Vishmika", "Jayasinghe", "vishmika@gmnail.com");
		Student tempStudent3 = new Student("Dilan", "Dananajaya", "dilan@gmnail.com");

		//save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}
	*/


	/*
	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("yasiru", "wickramasinghe", "yasiru@gmnail.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the save student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
	*/

}
