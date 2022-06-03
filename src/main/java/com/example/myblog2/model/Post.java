package com.example.myblog2.model;

import com.example.myblog2.dto.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@ToString
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = false)
    private String title;

    @Column(nullable = false, unique = false)
    private String message;

    @OneToMany
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"post"})
    @OrderBy("createdAt desc")
    List<Comment> comment;


    public Post(PostRequestDto postRequestDto) {
        this.user = postRequestDto.getUser();
        this.title = postRequestDto.getTitle();
        this.message = postRequestDto.getMessage();
    }

    public void setPost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.message = postRequestDto.getMessage();
    }

}
