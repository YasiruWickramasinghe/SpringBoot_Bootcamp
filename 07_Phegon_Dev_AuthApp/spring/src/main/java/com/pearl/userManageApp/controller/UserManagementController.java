package com.pearl.userManageApp.controller;

import com.pearl.userManageApp.dto.ReqRes;
import com.pearl.userManageApp.entity.UsersEntity;
import com.pearl.userManageApp.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    @Autowired
    private UsersManagementService usersManagementService;

    // User Registration
    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    // User Login
    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    // Refreshed Jwt Token
    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    // Fetching All Users
    @GetMapping("/admin/users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());
    }

    // Fetching Individual User by their Id
    // Used when Administrative process
    @GetMapping("/admin/users/{userId}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUserById(userId));
    }

    // Fetching  Profile Information (emails extract from authentication manager)
    // Used when a user wants to retrieve their own profile information
    @GetMapping("/adminuser/profile")
    public ResponseEntity<ReqRes> getProfileByEmail(){
        // Get the authenticated user's email from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getProfileByEmail(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Update Profile Info (emails extract from authentication manager)
    // Used when a user wants to update their own profile information
    @PutMapping("/adminuser/profile")
    public ResponseEntity<ReqRes> updateProfile(@RequestBody UsersEntity updatedProfile) {
        // Get the authenticated user's email from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.updateProfile(updatedProfile, email);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    // Update User By Id
    @PutMapping("/admin/users/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody UsersEntity usersEntity){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, usersEntity));
    }

    // Delete User By Id
    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }

}
