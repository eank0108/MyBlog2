package com.example.myblog2.model;


import com.example.myblog2.dto.UserRequestDto;
import com.example.myblog2.dto.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private UserRoleEnum role;

    public User(UserRequestDto userRequestDto) {

        username = userRequestDto.getUsername();
        password = userRequestDto.getPassword();
        role = UserRoleEnum.USER;
    }
}
