package com.example.spring.security.resolver.Mutation;

import com.example.spring.security.Domain.vertex.User;
import com.example.spring.security.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Service;

@Service
public class SaveUser implements GraphQLMutationResolver {

    private final UserRepository userRepository;

    public SaveUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(User user){
        userRepository.save(user);
       return "user saved";
    }

}
