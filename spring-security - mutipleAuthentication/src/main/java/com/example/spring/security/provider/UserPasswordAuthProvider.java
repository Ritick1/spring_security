package com.example.spring.security.provider;

import com.example.spring.security.auth.UserPasswordAuthToken;
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

@Component
public class UserPasswordAuthProvider implements AuthenticationProvider {

    @Lazy
    @Autowired
    LoginUserDetailService loginUserDetailService;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = loginUserDetailService.loadUserByUsername(authentication.getName());

        if (passwordEncoder.matches(authentication.getCredentials()+"",userDetails.getPassword())){
            return new UserPasswordAuthToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
        }
        return (Authentication) new BadCredentialsException("failed authentication");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserPasswordAuthToken.class.equals(authentication);
    }
}
