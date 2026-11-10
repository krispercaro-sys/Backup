package com.spring.hibernate_advanced_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImplement implements AppDao {

    private final EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    @Override
    @Transactional
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return em.find(Instructor.class, id);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = em.createQuery(
                "SELECT i FROM Instructor i "
                        + "LEFT JOIN FETCH i.courses "
                        + "LEFT JOIN FETCH i.instructorDetail "
                        + "WHERE i.id = :id",
                Instructor.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return em.find(InstructorDetail.class, id);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c WHERE c.instructor.id = :id",
                Course.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                        + "LEFT JOIN FETCH c.reviews "
                        + "WHERE c.id = :id",
                Course.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentByCourseId(int id) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                        + "LEFT JOIN FETCH c.students "
                        + "WHERE c.id = :id",
                Course.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s "
                        + "LEFT JOIN FETCH s.courses "
                        + "WHERE s.id = :id",
                Student.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        em.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    @Override
    @Transactional
    public void deleteInstructorId(int id) {
        Instructor instructor = em.find(Instructor.class, id);
        if (instructor != null) {
            if (instructor.getCourses() != null) {
                for (Course course : instructor.getCourses()) {
                    course.setInstructor(null);
                }
            }
            em.remove(instructor);
        }
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(int id) {
        InstructorDetail detail = em.find(InstructorDetail.class, id);
        if (detail != null) {
            Instructor instructor = detail.getInstructor();
            if (instructor != null) {
                instructor.setInstructorDetail(null);
            }
            em.remove(detail);
        }
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = em.find(Instructor.class, id);
        if (instructor != null) {
            if (instructor.getCourses() != null) {
                for (Course course : instructor.getCourses()) {
                    course.setInstructor(null);
                }
            }
            em.remove(instructor);
        }
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = em.find(Course.class, id);
        if (course != null) {
            em.remove(course);
        }
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = em.find(Student.class, id);
        if (student != null && student.getCourses() != null) {
            for (Course course : student.getCourses()) {
                if (course.getStudents() != null) {
                    course.getStudents().remove(student);
                }
            }
            em.remove(student);
        } else if (student != null) {
            em.remove(student);
        }
    }
}
