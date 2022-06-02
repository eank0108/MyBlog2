package com.example.myblog2.dto;


import com.example.myblog2.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostsResponseDto {
    private Long id;

    private String title;
    private String message;
    private long createdAt;
    private String username;

    public PostsResponseDto(Post post) {
        this.id = post.getId();
        this.message = post.getMessage();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.username = post.getUser().getUsername();
    }
}
