package com.example.myblog2.handler;

import com.example.myblog2.dto.StatusDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");

        System.out.println("로그인 실패");
        //로그인 실패시 필요한 작업 추가
        new ResponseHandler(response, new StatusDto("fail", "닉네임 또는 패스워드를 확인해주세요"));
        // response.sendRedirect("/signin");
    }
}