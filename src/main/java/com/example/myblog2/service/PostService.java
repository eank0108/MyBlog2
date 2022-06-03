package com.example.myblog2.service;

import com.example.myblog2.dto.*;
import com.example.myblog2.model.Comment;
import com.example.myblog2.model.Post;
import com.example.myblog2.repository.CommentRepository;
import com.example.myblog2.repository.PostRepository;
import com.example.myblog2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    public List<PostsResponseDto> readPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public StatusDto createPost(PostRequestDto postRequestDto) {
        try {
            postRequestDto.setMessage(postRequestDto.getMessage().replace("\n","<br>"));
            if (postRequestDto.getMessage().equals("")) {
                return new StatusDto("fail", "생성 실패. 내용을 입력하세요.");
            }
            if (postRequestDto.getTitle().equals("")) {
                return new StatusDto("fail", "생성 실패. 제목을 입력하세요.");
            }
            postRepository.save(new Post(postRequestDto));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new StatusDto("fail", "생성 실패");
        }
        return new StatusDto("success", "생성 완료");
    }

    public Post readPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));
        System.out.println(post.toString());
        return post;
        // try {
        // } catch (NullPointerException e) {
        //     return new StatusDto("fail", "해당 글이 존재하지 않습니다.");
        // }
    }

    @Transactional
    public StatusDto updatePost(Long id, PostRequestDto postRequestDto, UserDetailsImpl authUser) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));
        if (postRequestDto.getMessage().equals("")) {
            return new StatusDto("fail", "생성 실패. 내용을 입력하세요.");
        }
        if (postRequestDto.getTitle().equals("")) {
            return new StatusDto("fail", "생성 실패. 제목을 입력하세요.");
        }

        if (!post.getUser().getId().equals(authUser.getUser().getId())) {
            return new StatusDto("fail", "작성자만 수정 가능합니다.");
        }
        postRequestDto.setMessage(postRequestDto.getMessage().replace("\n","<br>"));
        post.setPost(postRequestDto);
        return new StatusDto("success", "수정 성공");
    }

    public StatusDto deletePost(Long id, UserDetailsImpl authUser) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));

        if (!post.getUser().getId().equals(authUser.getUser().getId())) {
            return new StatusDto("fail", "작성자만 삭제 가능합니다.");
        }
        postRepository.deleteById(id);
        return new StatusDto("success", "삭제 성공");
    }

    public StatusDto createComment(CommentRequestDto commentRequestDto) {
        System.out.println(commentRequestDto.getPost().getId());
        Post post = postRepository.findById(commentRequestDto.getPost().getId()).orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));
        commentRequestDto.setPost(post);
        try {
            commentRequestDto.setMessage(commentRequestDto.getMessage().replace("\n","<br>"));
            if (commentRequestDto.getMessage().equals("")) {
                return new StatusDto("fail", "생성 실패. 내용을 입력하세요.");
            }
            commentRepository.save(new Comment(commentRequestDto));
        } catch (Exception e) {
            return new StatusDto("fail", "생성 실패");
        }
            return new StatusDto("success", "생성 완료");
    }

    @Transactional
    public StatusDto updateComment(Long id, CommentRequestDto commentRequestDto, UserDetailsImpl authUser) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));

        if (!comment.getUser().getId().equals(authUser.getUser().getId())) {
            return new StatusDto("fail", "작성자만 수정 가능합니다.");
        }
        commentRequestDto.setMessage(commentRequestDto.getMessage().replace("\n","<br>"));
        if (commentRequestDto.getMessage().equals("")) {
            return new StatusDto("fail", "생성 실패. 내용을 입력하세요.");
        }
        comment.setComment(commentRequestDto);
        return new StatusDto("success", "수정 성공");
    }

    public StatusDto deleteComment(Long id, UserDetailsImpl authUser) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));

        if (!comment.getUser().getId().equals(authUser.getUser().getId())) {
            return new StatusDto("fail", "작성자만 삭제 가능합니다.");
        }
        commentRepository.deleteById(id);
        return new StatusDto("success", "삭제 성공");
    }
}
