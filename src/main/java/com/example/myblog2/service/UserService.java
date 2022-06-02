package com.example.myblog2.service;

import com.example.myblog2.dto.StatusDto;
import com.example.myblog2.dto.StatusEnum;
import com.example.myblog2.dto.UserRequestDto;
import com.example.myblog2.model.User;
import com.example.myblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public StatusDto signup(UserRequestDto userRequestDto) {
        if (userRequestDto.getUsername() == null || userRequestDto.getPassword() == null) {
            return new StatusDto("fail", "아이디, 비밀번호를 입력하세요");
        }

        if (!Pattern.matches("^[a-zA-Z0-9]{3,}$", userRequestDto.getUsername())) {
            return new StatusDto("fail", "아이디는 영어, 숫자만 써야하고 3~20글자 만 가능합니다.");
        }

        if (!userRequestDto.getPassword().equals(userRequestDto.getPasswordCheck())) {
            //비밀번호, 비밀번호확인 다름
            return new StatusDto("fail","비밀번호를 확인해 주세요");
        }

        if (userRequestDto.getPassword().contains(userRequestDto.getUsername())) {
            return new StatusDto("fail", "비밀번호에는 닉네임과 같은 값이 포함될 수 없습니다.");
        }
        if (userRequestDto.getPassword().length() < 4) {
            return new StatusDto("fail", "비밀번호는 4글자 이상 입력해 주세요.");
        }
        User user = userRepository.findByUsername(userRequestDto.getUsername());
        if (user != null) {
            return new StatusDto("fail", "이미 존재하는 아이디 입니다.");
        }
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(new User(userRequestDto));

        return new StatusDto("success", "회원가입 성공");
    }

    // public StatusDto signin(UserRequestDto userRequestDto) {
    //     User user = userRepository.findByUsernameAndPassword(userRequestDto.getUsername(), passwordEncoder.encode(userRequestDto.getPassword()));
    //     if (user == null) {
    //         return new StatusDto("fail", "로그인 실패");
    //     }
    // }
}
