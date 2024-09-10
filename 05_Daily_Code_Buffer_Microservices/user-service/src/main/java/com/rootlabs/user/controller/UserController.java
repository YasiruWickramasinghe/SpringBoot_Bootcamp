package com.rootlabs.user.controller;

import com.rootlabs.user.VO.ResponseTemplateVO;
import com.rootlabs.user.entity.Users;
import com.rootlabs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public Users saveUser(@RequestBody Users user){

        log.info("inside saveUser of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){

        log.info("inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);
    }
}
