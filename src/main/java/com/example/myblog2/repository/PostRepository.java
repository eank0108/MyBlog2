package com.example.myblog2.repository;

import com.example.myblog2.dto.PostsResponseDto;
import com.example.myblog2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostsResponseDto> findAllByOrderByCreatedAtDesc();
}
