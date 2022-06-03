package com.example.myblog2.controller;

import com.example.myblog2.dto.StatusDto;
import com.example.myblog2.dto.UserRequestDto;
import com.example.myblog2.security.UserDetailsImpl;
import com.example.myblog2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @ResponseBody
    public StatusDto signup(@RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal UserDetailsImpl authUser) {
        System.out.println(authUser);
        if (authUser != null) {
            return new StatusDto("fail", "이미 로그인 되어 있습니다.");
        }
        return userService.signup(userRequestDto);
    }



    // @PostMapping("/signin")
    // @ResponseBody
    // public StatusDto signin(@RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal UserDetailsImlp authUser) {
    //     System.out.println(authUser);
    //     if (authUser != null) {
    //         return new StatusDto("fail", "이미 로그인 되어 있습니다.");
    //     }
    //     return userService.signup(userRequestDto);
    // }



    @GetMapping("/userinfo")
    @ResponseBody
    public UserDetailsImpl userInfo(@AuthenticationPrincipal UserDetailsImpl authUser) {
        System.out.println(authUser);

        return authUser;
    }


}
