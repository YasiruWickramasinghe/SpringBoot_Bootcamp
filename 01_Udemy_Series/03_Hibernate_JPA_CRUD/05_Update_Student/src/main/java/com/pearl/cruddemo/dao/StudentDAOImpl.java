package com.pearl.cruddemo.dao;

import com.pearl.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository annotation is specialized version of @Component Annotation
//support component scanning
//translate JDBC exceptions
@Repository
public class StudentDAOImpl implements StudentDAO {

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save student method
    @Override
    @Transactional // Important : use spring @transactional annotation not jakarta
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    //implement retrieve student method
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        //return query result
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);

        //set query parameters
        theQuery.setParameter("theData", theLastName);

        //return query result
        return theQuery.getResultList();
    }

    @Override
    @Transactional // use this when we perform update here
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

}
