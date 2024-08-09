package com.pearl.cruddemo.dao;

import com.pearl.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    //implement save method
    @Override
    @Transactional // Important : use spring @transactional annotation not jakarta
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
    
}
