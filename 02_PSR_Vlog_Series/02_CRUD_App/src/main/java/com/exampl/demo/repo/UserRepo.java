package com.exampl.demo.repo;

import com.exampl.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    //for example purpose
    //Create Query for get User By id for Example
    @Query(value = "SELECT * FROM User WHERE id = ?1", nativeQuery = true)
    User getUserById(Integer userId);

}
