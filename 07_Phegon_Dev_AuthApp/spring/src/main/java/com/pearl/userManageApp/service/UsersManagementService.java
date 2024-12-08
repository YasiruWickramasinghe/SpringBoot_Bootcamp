package com.pearl.userManageApp.service;

import com.pearl.userManageApp.dto.ReqRes;
import com.pearl.userManageApp.entity.UsersEntity;
import com.pearl.userManageApp.repository.UserRepository;
import com.pearl.userManageApp.security.JwtUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersManagementService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // User Registration
    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            UsersEntity userEntity = new UsersEntity();
            userEntity.setEmail(registrationRequest.getEmail());
            userEntity.setCity(registrationRequest.getCity());
            userEntity.setRole(registrationRequest.getRole());
            userEntity.setName(registrationRequest.getName());
            userEntity.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            UsersEntity usersEntityResult = userRepository.save(userEntity);

            if (usersEntityResult.getId()>0) {
                resp.setUsersEntity((usersEntityResult));
                resp.setMessage("User Registration Successfully!");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError("An error occurred during user registration: " + e.getMessage());
        }
        return  resp;
    }

    // User Login
    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword())
                    );
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In!");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("An error occurred during user login: " + e.getMessage());
        }
        return response;
    }

    // Refreshed Jwt Token
    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();

        try {
            String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            UsersEntity users = userRepository.findByEmail(ourEmail).orElseThrow();

            if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hrs");
                response.setMessage("Successfully Refreshed Token!");
            }
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("An error occurred while refreshing the token: " + e.getMessage());
            return response;
        }
    }

    // Fetching All Users
    public ReqRes getAllUsers(){
        ReqRes reqRes = new ReqRes();

        try {
            List<UsersEntity> result = userRepository.findAll();

            if(!result.isEmpty()) {
                reqRes.setUsersEntityList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successfully  Retrieved All Users!");
            } else {
             reqRes.setStatusCode(404);
                reqRes.setMessage("Users Not Found!");
            }
            return reqRes;
        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("An error occurred while retrieving all users: " + e.getMessage());
            return reqRes;
        }
    }

    // Fetching Individual User by their Id
    // Used when Administrative process
    public ReqRes getUserById(Integer userId){
        ReqRes reqRes = new ReqRes();

        try {
            UsersEntity usersById = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found!"));
            reqRes.setUsersEntity(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with Id '" + userId + "' Found Successfully!");
        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("An error occurred while fetching the user: " + e.getMessage());
        }
        return reqRes;
    }

    // Fetching Profile Information By Email (emails extract from authentication manager)
    // Used when a user wants to retrieve their own profile information
    public ReqRes getProfileByEmail(String userEmail){
        ReqRes reqRes = new ReqRes();

        try {
            Optional<UsersEntity> userEntityOptional = userRepository.findByEmail(userEmail);
            if(userEntityOptional.isPresent()){
                reqRes.setUsersEntity(userEntityOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("Users with Email '" + userEmail + "', Profile Information Fetched Successfully!");
            }
            else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User Not Found for Fetching Profile Info!");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("An error occurred while fetching user profile info: " + e.getMessage());
        }
        return reqRes;
    }

    // Update Profile Information (emails extract from authentication manager)
    // Used when a user wants to update their own profile information
    // User Cannot Update their own email and roll
    public ReqRes updateProfile(UsersEntity updatedProfile, String userEmail) {
        ReqRes reqRes = new ReqRes();

        try {
            Optional<UsersEntity> userEntityOptional = userRepository.findByEmail(userEmail);
            if (userEntityOptional.isPresent()) {
                UsersEntity existingUser = userEntityOptional.get();
                existingUser.setName(updatedProfile.getName());
                existingUser.setCity(updatedProfile.getCity());

                // Check if password is present and update it
                if (updatedProfile.getPassword() != null && !updatedProfile.getPassword().isEmpty()) {
                    existingUser.setPassword(passwordEncoder.encode(updatedProfile.getPassword()));
                }

                UsersEntity savedUser = userRepository.save(existingUser);
                reqRes.setUsersEntity(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Profile updated successfully!");

            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for profile update!");
            }

        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("An error occurred while updating the profile: " + e.getMessage());
        }

        return reqRes;
    }


    // Update User By Id
    public ReqRes updateUser(Integer userId, UsersEntity updateUser){
        ReqRes reqRes = new ReqRes();

        try {
            Optional<UsersEntity> userEntityOptional = userRepository.findById(userId);
            if(userEntityOptional.isPresent()){
                UsersEntity existingUser = userEntityOptional.get();
                existingUser.setEmail(updateUser.getEmail());
                existingUser.setName(updateUser.getName());
                existingUser.setCity(updateUser.getCity());
                existingUser.setRole(updateUser.getRole());

                // Check if password is present in the request
                if(updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()){
                    // Encode the password and Update
                    existingUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
                }

                UsersEntity savedUser = userRepository.save(existingUser);
                reqRes.setUsersEntity(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Users with Id '" + userId + "' Updated Successfully!");
            }else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("Users Not Found for Update!");
            }
        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("An error occurred while updating the user: " + e.getMessage());
        }
        return reqRes;
    }

    // Delete User By Id
    public ReqRes deleteUser(Integer userId){
        ReqRes reqRes = new ReqRes();

        try {
            Optional<UsersEntity> userEntityOptional = userRepository.findById(userId);
            if(userEntityOptional.isPresent()){
                userRepository.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Users with Id '" + userId + "' Deleted Successfully!");
            }else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("Users Not Found for Deletion!");
            }
        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("An error occurred while deleting the user: " + e.getMessage());
        }
        return reqRes;
    }


}
