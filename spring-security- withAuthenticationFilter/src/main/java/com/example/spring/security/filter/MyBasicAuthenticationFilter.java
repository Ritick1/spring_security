package com.example.spring.security.filter;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Component
public class MyBasicAuthenticationFilter implements Filter {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authKey = httpServletRequest.getHeader("auth_key");
        if(authKey == null){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        MyAuthenticationToken myAuthenticationToken = new MyAuthenticationToken(authKey, null);
        try {
            Authentication authenticate = authenticationManager.authenticate(myAuthenticationToken);

            if(authenticate.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }catch (AuthenticationException authenticationException){
            new BadCredentialsException("invaild exception "+authenticationException);
        }

    }
}
