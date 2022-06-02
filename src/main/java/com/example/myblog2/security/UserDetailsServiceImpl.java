package com.example.myblog2.security;

import com.example.myblog2.model.User;
import com.example.myblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public UserDetailsImlp loadUserByUsername(String username) throws UsernameNotFoundException, IllegalStateException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("닉네임 또는 패스워드를 확인해주세요.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            System.out.println(((UserDetailsImlp)authentication.getPrincipal()).getUser());
            // UserDetails userDetails = new UserDetailsImlp(((UserDetailsImlp)authentication.getPrincipal()).getUser());
            System.out.println("이미 로그인 되어 있습니다.");
            throw new IllegalStateException("이미 로그인 되어있습니다");
        }

        return new UserDetailsImlp(user);
    }
}
