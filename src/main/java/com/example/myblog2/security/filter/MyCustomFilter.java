package com.example.myblog2.security.filter;

import com.example.myblog2.dto.StatusDto;
import com.example.myblog2.handler.ResponseHandler;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MyCustomFilter extends OncePerRequestFilter {

    // @Override
    // public void init(FilterConfig filterConfig) throws ServletException {
    //     Filter.super.init(filterConfig);
    // }

    // @Override
    // public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    //     System.out.println("MyCustomFilter");
    //     HttpServletRequest httpRequest = (HttpServletRequest) request;
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     if (request instanceof HttpServletRequest && httpRequest.getMethod().equals("POST") && httpRequest.getRequestURI().equals("/signin") && authentication != null) {
    //         System.out.println("이미 로그인 되어 있습니다.");
    //         new ResponseHandler(response, new StatusDto("fail", "이미 로그인 되어 있습니다."));
    //         // responseHandler.responseFlush();
    //
    //
    //     }else {
    //         chain.doFilter(request, response);
    //     }
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("MyCustomFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (request instanceof HttpServletRequest && httpRequest.getMethod().equals("POST") && httpRequest.getRequestURI().equals("/signin") && authentication != null) {
            System.out.println("이미 로그인 되어 있습니다.");
            new ResponseHandler(response, new StatusDto("fail", "이미 로그인 되어 있습니다."));
            // responseHandler.responseFlush();


        }else {
            filterChain.doFilter(request, response);
        }
    }

    // @Override
    // public void destroy() {
    //     Filter.super.destroy();
    // }
}