package com.example.myblog2.security;


import com.example.myblog2.handler.AuthenticationFailureHandlerImpl;
import com.example.myblog2.handler.AuthenticationSuccessHandlerImpl;
import com.example.myblog2.security.filter.FormLoginFilter;
import com.example.myblog2.security.filter.JwtFilter;
import com.example.myblog2.security.filter.MyCustomFilter;
import com.example.myblog2.security.jwt.FilterSkipMatcher;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .addFilterBefore(myCustomFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(formLoginFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(),UsernamePasswordAuthenticationFilter.class)
                ;

        http.authorizeRequests()
                .antMatchers("/signup", "/logout","/","/posts","/post","/post/**","/comment","/login","/comment/**","/alert","/detail/**",
                        // static
                        "/css/**", "/img/**", "/js/**",
                        // swagger v3
                        "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                // .and()
                // .formLogin()
                // .loginPage("/login")
                // .loginProcessingUrl("/signin")
                // .successHandler(authenticationSuccessHandler())
                // .failureHandler(authenticationFailureHandler())
                // // .failureUrl("/login")
                // // .defaultSuccessUrl("/",true)
                // .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")

        ;
    }

    public JwtFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();
        // Static 정보 접근 허용
        skipPathList.add("GET,/images/**");
        skipPathList.add("GET,/css/**");


        skipPathList.add("GET,/signup");
        skipPathList.add("POST,/signup");
        skipPathList.add("GET,/logout");
        skipPathList.add("GET,/");
        skipPathList.add("GET,/posts");
        skipPathList.add("GET,/post");
        skipPathList.add("GET,/post/**");
        skipPathList.add("GET,/comment");
        skipPathList.add("GET,/login");
        skipPathList.add("GET,/comment/**");
        skipPathList.add("GET,/alert");
        skipPathList.add("GET,/detail/**");

        // static
        skipPathList.add("GET,/css/**");
        skipPathList.add("GET,/img/**");
        skipPathList.add("GET,/js/**");

        // swagger v3
        skipPathList.add("GET,/v2/api-docs");
        skipPathList.add("GET,/configuration/**");
        skipPathList.add("GET,/swagger*/**");
        skipPathList.add("GET,/webjars/**");

        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPathList,
                "/**"
        );
        JwtFilter jwtFilter = new JwtFilter(matcher);
        jwtFilter.setAuthenticationManager(authenticationManager());
        return jwtFilter;
    }

    // @Bean
    public FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter formLoginFilter = new FormLoginFilter();
        formLoginFilter.setAuthenticationManager(authenticationManager());
        formLoginFilter.setFilterProcessesUrl("/signin");
        formLoginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        formLoginFilter.setAuthenticationFailureHandler(authenticationFailureHandler());


        return formLoginFilter;
    }
    // @Bean
    public MyCustomFilter myCustomFilter() throws Exception {


        return new MyCustomFilter();
    }
    // @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandlerImpl();
    }
    // @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandlerImpl();
    }
}
