package com.exampl.demo.service;

import com.exampl.demo.dto.UserDTO;
import com.exampl.demo.model.User;
import com.exampl.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
     @Autowired
    private ModelMapper modelMapper;

    //Get All Users
    public List<UserDTO> getAllUsers(){
        List<User>userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
    }

    //Get User By ID
    public UserDTO getUserById(Integer userId){
        User user = userRepo.getUserById(userId);
        return modelMapper.map(user, UserDTO.class);
    }

    //Create User
    public UserDTO saveUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    //Update Existing User if not existing add new entry
    public UserDTO updateUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    // 01) Delete User - catch the id in request body and delete the record
//    public String deleteUser(UserDTO userDTO){
//        userRepo.delete(modelMapper.map(userDTO, User.class));
//        return "User Record Deleted";
//    }

    // 02) Delete User - catch the id in browser url and delete the record
    public String deleteUser(Integer userId){
        userRepo.deleteById(userId);
        return "User Record Deleted";
    }

}
