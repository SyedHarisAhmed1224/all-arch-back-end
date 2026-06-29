package com.allarch.all_arch_back_end.security;

public class CustomUserPrincipal {

    private final Integer userId;
    private final String email;

    public CustomUserPrincipal(Integer userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}