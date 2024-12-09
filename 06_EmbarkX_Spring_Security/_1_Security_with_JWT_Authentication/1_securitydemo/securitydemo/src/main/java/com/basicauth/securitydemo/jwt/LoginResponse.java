package com.basicauth.securitydemo.jwt;

import java.util.List;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;

    private String username;
    private List<String> roles;

    public LoginResponse(String username, List<String> roles, String accessToken, String refreshToken) {
        this.username = username;
        this.roles = roles;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}


