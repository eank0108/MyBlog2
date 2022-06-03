package com.example.myblog2.handler;

import com.example.myblog2.dto.StatusDto;
import com.example.myblog2.dto.StatusEnum;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ResponseHandler {
    private ServletResponse response;
    private StatusDto statusDto;

    public ResponseHandler(ServletResponse response, StatusDto statusDto) {
        this.response = response;
        this.statusDto = statusDto;
        this.responseFlush();
    }

    public void responseFlush() {
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
            out.print(mapper.writeValueAsString(statusDto));
            // ((HttpServletResponse) response).sendRedirect("/");
            out.flush();
        } catch (IOException e) {
            System.out.println("Response Error");
        }
    }
}
