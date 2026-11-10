package com.spring.hibernate_advanced_demo.dao;

import java.util.List;

import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Student;

public interface AppDao {

    void save(Instructor instructor);

    void save(Course course);

    Instructor findInstructorById(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    InstructorDetail findInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Course findCourseAndReviewsByCourseId(int id);

    Course findCourseAndStudentByCourseId(int id);

    Student findStudentAndCoursesByStudentId(int id);

    void update(Instructor instructor);

    void update(Student student);

    void deleteInstructorId(int id);

    void deleteInstructorDetail(int id);

    void deleteInstructorById(int id);

    void deleteCourseById(int id);

    void deleteStudentById(int id);
}
