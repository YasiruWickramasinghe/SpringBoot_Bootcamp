package com.exampl.demo.controller;

import com.exampl.demo.dto.UserDTO;
import com.exampl.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    //getAllUsers API
    @GetMapping("/getUsers")
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    //createUser API
    @PostMapping("/addUser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    //updateUser API
    @PutMapping("updateUser")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return  userService.updateUser(userDTO);
    }

    //deleteUser API - using catch userId in request parameter
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody UserDTO userDTO){
        return  userService.deleteUser(userDTO);
    }
}
