package com.pearl.userManageApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pearl.userManageApp.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String email;
    private String name;
    private String password;
    private String city;
    private String role;
    private UserEntity userEntity;
    private String userList;
    private List<UserEntity> userEntityList;
}
