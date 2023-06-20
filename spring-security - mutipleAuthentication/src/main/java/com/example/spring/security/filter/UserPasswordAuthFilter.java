package com.example.spring.security.filter;

import com.arangodb.springframework.core.ArangoOperations;
import com.example.spring.security.Domain.vertex.User;
import com.example.spring.security.auth.OtpAuthToken;
import com.example.spring.security.auth.UserPasswordAuthToken;
import com.example.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Component
public class UserPasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    private final ArangoOperations arangoOperations;

    private final UserRepository userRepository;
    public UserPasswordAuthFilter( ArangoOperations arangoOperations, UserRepository userRepository) {
        this.arangoOperations = arangoOperations;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String userName = request.getHeader("userName");
        String password = request.getHeader("password");
        String key = request.getHeader("secret-key");

        if(key == null){
            if(userName != null && password != null) {
                UserPasswordAuthToken userPasswordAuthToken = new UserPasswordAuthToken(userName, password);
                authenticationManager.authenticate(userPasswordAuthToken);
                User user = userRepository.findByUsername(userName).orElse(new User());
                user.setOtp((new Random().nextInt(999) * 1000) + "");
                arangoOperations.update(user.getId(), user);
            }else {
                filterChain.doFilter(request,response);
            }
        }else {
            authenticationManager.authenticate(new OtpAuthToken(userName,key));
            response.setHeader("Authorization", UUID.randomUUID().toString());
        }
    }
}
