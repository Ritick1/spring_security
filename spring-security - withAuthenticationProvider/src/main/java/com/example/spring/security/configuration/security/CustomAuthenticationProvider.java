package com.example.spring.security.configuration.security;

import com.example.spring.security.service.LoginUserDetailService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final LoginUserDetailService loginUserDetailService;

    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(LoginUserDetailService loginUserDetailService, PasswordEncoder passwordEncoder) {
        this.loginUserDetailService = loginUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String credentials = authentication.getCredentials().toString();
        UserDetails userDetails = loginUserDetailService.loadUserByUsername(authentication.getName());
        if(userDetails != null && passwordEncoder.matches(credentials,userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(authentication.getName(),authentication.getCredentials().toString());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
