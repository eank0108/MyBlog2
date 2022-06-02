package com.example.myblog2.dto;

import com.example.myblog2.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PostRequestDto {

    private User user;

    private String title;

    private String message;
}
