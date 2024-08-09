package com.pearl.cruddemo;

import com.pearl.cruddemo.dao.StudentDAO;
import com.pearl.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

		return runner -> {
			//createStudent(studentDAO);

			createMultipleStudents(studentDAO);
		};
	}

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

}
