package com.example.spring.security.resolver.Query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;

@Service
public class HelloResolver implements GraphQLQueryResolver {

    public String getHello(){
        return "hello 2-step authentication!";
    }
}
