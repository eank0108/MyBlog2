package com.example.myblog2.security;

import com.example.myblog2.model.User;
import com.example.myblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException, IllegalStateException {
        System.out.println("loadUserByUsername");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("닉네임 또는 패스워드를 확인해주세요.");
        }


        return new UserDetailsImpl(user);
    }
}
