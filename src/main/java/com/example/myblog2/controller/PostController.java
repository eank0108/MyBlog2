package com.example.myblog2.controller;

import com.example.myblog2.dto.CommentRequestDto;
import com.example.myblog2.dto.PostRequestDto;
import com.example.myblog2.dto.PostsResponseDto;
import com.example.myblog2.dto.StatusDto;
import com.example.myblog2.model.Post;
import com.example.myblog2.security.UserDetailsImlp;
import com.example.myblog2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<PostsResponseDto> readPosts() {
        return postService.readPosts();
    }

    @PostMapping("/post")
    public StatusDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImlp authUser) {

        if (authUser == null) {
            return new StatusDto("fail", "로그인 후 작성 가능.");
        }
        postRequestDto.setUser(authUser.getUser());
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/post/{id}")
    public Post readPost(@PathVariable Long id) {
        return postService.readPost(id);
    }

    @PutMapping("/post/{id}")
    public StatusDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImlp authUser) {
        if (authUser == null) {
            return new StatusDto("fail", "로그인 후 수정 가능.");
        }
        return postService.updatePost(id, postRequestDto, authUser);
    }
    @DeleteMapping("/post/{id}")
    public StatusDto deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImlp authUser) {
        if (authUser == null) {
            return new StatusDto("fail", "로그인 후 삭제 가능.");
        }
        return postService.deletePost(id, authUser);
    }




    @PostMapping("/comment")
    // @Secured("USER")
    public StatusDto createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImlp authUser) {
        if (authUser == null) {
            return new StatusDto("fail", "로그인 후 작성 가능.");
        }
        commentRequestDto.setUser(authUser.getUser());
        return postService.createComment(commentRequestDto);
    }

    @PutMapping("/comment/{id}")
    public StatusDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImlp authUser) {
        if (authUser == null) {
            return new StatusDto("fail", "로그인 후 수정 가능.");
        }
        return postService.updateComment(id, commentRequestDto, authUser);
    }
    @DeleteMapping("/comment/{id}")
    public StatusDto deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImlp authUser) {
        if (authUser == null) {
            return new StatusDto("fail", "로그인 후 삭제 가능.");
        }
        return postService.deleteComment(id, authUser);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> allException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
