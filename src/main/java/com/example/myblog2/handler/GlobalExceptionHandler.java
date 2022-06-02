package com.example.myblog2.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// @ControllerAdvice
// @RestController
// public class GlobalExceptionHandler {
//
//     @ExceptionHandler(value=Exception.class)
//     public ErrorDto<String> handleArgumentException(Exception e) {
//         System.out.println("로그인 실패 에러 핸들러");
//         return new ErrorDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()); // 500
//     }
// }
