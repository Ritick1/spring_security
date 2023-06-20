package com.example.spring.security.provider;

import com.example.spring.security.Domain.vertex.User;
import com.example.spring.security.auth.OtpAuthToken;
import com.example.spring.security.auth.UserPasswordAuthToken;
import com.example.spring.security.repository.UserRepository;
import com.example.spring.security.service.LoginUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Component
public class OtpAuthProvider implements AuthenticationProvider {

    @Autowired
    @Lazy
    UserRepository userRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<User> byUsername = userRepository.findByUsername(authentication.getName());

        if(byUsername.isPresent()){
            return new OtpAuthToken(authentication.getName(),authentication.getCredentials(), Arrays.asList(()->"read"));
        }
        return (Authentication) new BadCredentialsException("failed to authenticate otp");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthToken.class.equals(authentication);
    }
}
