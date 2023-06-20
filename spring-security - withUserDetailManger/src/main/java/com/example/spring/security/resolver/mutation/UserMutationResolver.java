package com.example.spring.security.resolver.mutation;

import com.example.spring.security.Domain.User;
import com.example.spring.security.service.LoginUserDetailService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Service;

@Service
public class UserMutationResolver implements GraphQLMutationResolver {

    private final LoginUserDetailService loginUserDetailService;

    public UserMutationResolver(LoginUserDetailService loginUserDetailService) {
        this.loginUserDetailService = loginUserDetailService;
    }

    public String createUser(User user){
        loginUserDetailService.createUser(user);
        return "user created";
    }
}
