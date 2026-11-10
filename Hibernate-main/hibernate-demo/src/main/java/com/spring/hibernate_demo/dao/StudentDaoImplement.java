package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.spring.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImplement implements StudentDao {

    private EntityManager em;

    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // 1. Retrieve the student record by ID
        Student student = em.find(Student.class, id);

        // 2. Perform removal only if the entity exists
        if (student != null) {
            em.remove(student);
            System.out.println("Deleted student with ID: " + id);
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }

     @Override
    @Transactional
    public int deleteAll() {
        int numRows = em.createQuery("DELETE FROM Student").executeUpdate();
        return numRows;
    }



    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return em.createQuery("FROM Student ORDER BY lastName asc", Student.class).getResultList();
    }

    @Override 
    public List<Student> findByLastName(String lastname) {
        TypedQuery<Student> query = em.createQuery("FROM Student WHERE lastName = :data", Student.class);
        query.setParameter("data", lastname);
        return query.getResultList();
    }
}