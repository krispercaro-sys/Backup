package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Student;
import java.util.List;

public interface StudentDao {

        void save(Student theStudent);
        Student findById(Integer id);
        List<Student> findAll();
        List<Student> findByLastName(String lastname);
        void update(Student theStudent);
        void delete(Integer id);
        int deleteAll();
}
