package com.example.myblog2.security.filter;

import com.example.myblog2.dto.StatusDto;
import com.example.myblog2.handler.ResponseHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("FormLoginFilter");
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UsernamePasswordAuthenticationToken authRequestToken = null;
        try {
            JsonNode requestBody = objectMapper.readTree(request.getInputStream());
            String username = requestBody.get("username").asText();
            String password = requestBody.get("password").asText();
            authRequestToken = new UsernamePasswordAuthenticationToken(username, password);
        } catch (IOException e) {
            new ResponseHandler(response, new StatusDto("fail", "올바른 정보를 입력해 주세요."));
        }

        setDetails(request,authRequestToken);
        return this.getAuthenticationManager().authenticate(authRequestToken);
    }
}
