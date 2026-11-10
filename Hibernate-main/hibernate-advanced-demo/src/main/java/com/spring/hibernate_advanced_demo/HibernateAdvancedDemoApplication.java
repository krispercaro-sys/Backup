package com.spring.hibernate_advanced_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_advanced_demo.dao.AppDao;
import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Review;
import com.spring.hibernate_advanced_demo.entity.Student;

@SpringBootApplication
public class HibernateAdvancedDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                HibernateAdvancedDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            AppDao app,
            @Value("${hibernate.demo.run:false}") boolean runDemo) {
        return runner -> {
            if (runDemo) {
                createInstructorWithCourses(app);
            }
        };
    }

    private void createInstructorWithCourses(AppDao app) {
        Instructor instructor = new Instructor(
                "Peri", "Winkle", "peri.winkle@example.com");
        instructor.setInstructorDetail(
                new InstructorDetail("Physics", "Reading"));

        Course course = new Course("Hibernate Basics");
        course.addReview(new Review("Useful course"));
        Student student = new Student(
                "Light", "Yagami", "light@example.com");
        student.addCourse(course);
        instructor.addCourse(course);

        app.save(instructor);
    }
}
