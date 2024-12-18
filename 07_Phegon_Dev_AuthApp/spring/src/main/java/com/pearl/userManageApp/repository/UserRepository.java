package com.pearl.userManageApp.repository;

import com.pearl.userManageApp.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

    Optional<UsersEntity> findByEmail(String email);
}
