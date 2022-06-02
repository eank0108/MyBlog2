package com.example.myblog2.dto;

public enum StatusEnum {
    SUCCESS("success"),
    FAIL("fail");

    final private String status;

    StatusEnum(String status) {
        this.status = status;
    }
}
