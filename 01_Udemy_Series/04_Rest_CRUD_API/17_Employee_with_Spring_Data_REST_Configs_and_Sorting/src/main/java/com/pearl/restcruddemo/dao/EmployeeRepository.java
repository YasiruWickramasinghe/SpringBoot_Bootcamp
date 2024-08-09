package com.pearl.restcruddemo.dao;

import com.pearl.restcruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//mention Entity type and Primary Key <EntityType, PrimaryKeyType>
@RepositoryRestResource(path="members") // change path "/members"
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //that's it... no need  to write any code LOL!
}
