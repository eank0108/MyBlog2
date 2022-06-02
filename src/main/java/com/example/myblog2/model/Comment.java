package com.example.myblog2.model;

import com.example.myblog2.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto commentRequestDto) {
        this.message = commentRequestDto.getMessage();
        this.user = commentRequestDto.getUser();
        this.post = commentRequestDto.getPost();
    }

    public void setComment(CommentRequestDto commentRequestDto) {
        this.message = commentRequestDto.getMessage();
    }
}
