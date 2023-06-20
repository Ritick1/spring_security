package com.example.spring.security.service;

import com.example.spring.security.Domain.User;
import com.example.spring.security.Domain.dto.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailService implements UserDetailsService{

    private final LoginUserRepository loginUserRepository;

    public LoginUserDetailService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = loginUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
       return new LoginUser(user);
    }
}
