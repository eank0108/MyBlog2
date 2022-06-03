package com.example.myblog2.security.provider;

import com.example.myblog2.handler.ResponseHandler;
import com.example.myblog2.security.UserDetailsImpl;
import com.example.myblog2.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
// public class FormLoginAuthProvider implements AuthenticationProvider {
//
//     private UserDetailsServiceImpl userDetailsServiceImpl;
//     private BCryptPasswordEncoder passwordEncoder;
//
//     @Autowired
//     public FormLoginAuthProvider(UserDetailsServiceImpl userDetailsService,BCryptPasswordEncoder passwordEncoder) {
//         this.userDetailsServiceImpl = userDetailsService;
//         this.passwordEncoder = passwordEncoder;
//     }
//
//     @Override
//     public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//         UsernamePasswordAuthenticationToken authRequestToken = (UsernamePasswordAuthenticationToken) authentication;
//
//         String username = authRequestToken.getName();
//         String password = (String) authRequestToken.getCredentials();
//         UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);
//
//         if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//             throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
//         }
//
//         return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//     }
//
//     @Override
//     public boolean supports(Class<?> authentication) {
//         return authentication.equals(UsernamePasswordAuthenticationToken.class);
//     }
// }
