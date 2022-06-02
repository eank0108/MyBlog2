package com.example.myblog2.service;

import com.example.myblog2.dto.StatusDto;
import com.example.myblog2.dto.StatusEnum;
import com.example.myblog2.dto.UserRequestDto;
import com.example.myblog2.model.User;
import com.example.myblog2.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 닉네임")
    void signUp_Username() {
        UserService userService= new UserService(userRepository, passwordEncoder);

        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setUsername("aa");
        userRequestDto.setPassword("12341234");
        userRequestDto.setPasswordCheck("12341234");

        assertEquals(userService.signup(userRequestDto).getMessage(),
                "아이디는 영어, 숫자만 써야하고 3~20글자 만 가능합니다.");


        userRequestDto.setUsername("한글테스트");

        assertEquals(userService.signup(userRequestDto).getMessage(),
                "아이디는 영어, 숫자만 써야하고 3~20글자 만 가능합니다.");

    }

    @Test
    @DisplayName("회원가입 비밀번호")
    void signUp_Password() {
        UserService userService= new UserService(userRepository, passwordEncoder);

        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setUsername("test");
        userRequestDto.setPassword("123");
        userRequestDto.setPasswordCheck("123");

        assertEquals(userService.signup(userRequestDto).getMessage(),
                "비밀번호는 4글자 이상 입력해 주세요.");


        userRequestDto.setPassword("test123");
        userRequestDto.setPasswordCheck("test123");

        assertEquals(userService.signup(userRequestDto).getMessage(),
                "비밀번호에는 닉네임과 같은 값이 포함될 수 없습니다.");

    }

    @Test
    @DisplayName("회원가입 비밀번호 일치 확인")
    void signUp_Password_Check() {
        UserService userService= new UserService(userRepository, passwordEncoder);

        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setUsername("test");
        userRequestDto.setPassword("123");
        userRequestDto.setPasswordCheck("1243");

        assertEquals(userService.signup(userRequestDto).getMessage(),
                "비밀번호를 확인해 주세요");


        userRequestDto.setPassword("wfef21edasf");
        userRequestDto.setPasswordCheck("f312fsegasfse");

        assertEquals(userService.signup(userRequestDto).getMessage(),
                "비밀번호를 확인해 주세요");

    }

    @Test
    @DisplayName("회원가입 닉네임 중복")
    void signUp_Username_Duplicate() {
        UserService userService= new UserService(userRepository, passwordEncoder);

        UserRequestDto userRequestDto = new UserRequestDto();



        userRequestDto.setUsername("test");
        userRequestDto.setPassword("1234");
        userRequestDto.setPasswordCheck("1234");

        User user = new User(userRequestDto);
        when(userRepository.findByUsername(userRequestDto.getUsername()))
                .thenReturn(user);

        assertEquals(userService.signup(userRequestDto).getMessage(),
                "이미 존재하는 아이디 입니다.");


        userRequestDto.setUsername("test2");

        User user2 = new User(userRequestDto);
        when(userRepository.findByUsername(userRequestDto.getUsername()))
                .thenReturn(user2);

        assertEquals(userService.signup(userRequestDto).getMessage(),
                "이미 존재하는 아이디 입니다.");

    }


}