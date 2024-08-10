package com.pearl.restcruddemo.dao;

import com.pearl.restcruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//mention Entity type and Primary Key <EntityType, PrimaryKeyType>
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //that's it... no need  to write any code LOL!
}
