package com.example.myblog2.dto;

import com.example.myblog2.model.Comment;
import com.example.myblog2.model.User;

import java.util.List;

public interface PostResponseMapping {
    Long getId();

    User getUser();

    String getTitle();

    String getMessage();

    List<Comment> getComment();

}
