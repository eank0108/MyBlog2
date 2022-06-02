package com.example.myblog2.dto;

public enum UserRoleEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    final private String role;

    UserRoleEnum(String role) {
        this.role = role;
    }
}
