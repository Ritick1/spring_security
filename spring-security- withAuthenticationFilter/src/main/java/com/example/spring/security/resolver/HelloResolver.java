package com.example.spring.security.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;

@Service
public class HelloResolver implements GraphQLQueryResolver {

    public String getHello(){
        return "hi spring security!";
    }
}
