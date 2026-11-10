package com.spring.hibernate_demo;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			// readStudent(studentDao);
			// queryStudentLastName(studentDao);
			// queryStudents(studentDao);
			// updateStudent(studentDao);
			//deleteStudent(studentDao);
			deleteAllStudent(studentDao);
		};
	}

	private void deleteAllStudent(StudentDao studentDao){
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of deleted rowsL " + rowsDeleted);
	}

	private void deleteStudent(StudentDao studentDao){
		int id = 16;
		System.out.println("Delete ID: " + id);

		studentDao.delete(id);
	}

	private void updateStudent(StudentDao studentDao){
		int id = 1;
		System.out.println("Student ID: " + id);
		Student student = studentDao.findById(id);

		System.out.println("Updating student name...");
		student.setFirstName("MIGUELITO");

		studentDao.update(student);

		System.out.println("Updated student information: " + student);
	}

	private void queryStudentLastName(StudentDao studentDao){

		List<Student> students = studentDao.findByLastName("Nicanor");

		for (Student s : students){
			System.out.println(s);
		}
	}

	private void queryStudents(StudentDao studentDao){

		List<Student> students = studentDao.findAll();

		for(Student s:students){
			System.out.println(s);
		}
	}

	private void readStudent(StudentDao studentDao) {
		
		System.out.println("Creating student information...");
		Student tempStudent = new Student("Vergel", "Nicanor", "Vergel.Nicanor@example.com");

		System.out.println("Saving student information...");
		studentDao.save(tempStudent);

		int theId = tempStudent.getId();
		System.out.println("Saved! Generated id: " + theId);

		System.out.println("Retrieving student id " + theId + "...");
		Student myStudent = studentDao.findById(theId);

		System.out.println("Found the student " + myStudent);
	}
}