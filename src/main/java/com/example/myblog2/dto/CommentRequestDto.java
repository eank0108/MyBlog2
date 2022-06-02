package com.example.myblog2.dto;

import com.example.myblog2.model.Post;
import com.example.myblog2.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class CommentRequestDto {

    private String message;
    private User user;
    private Post post;
}
