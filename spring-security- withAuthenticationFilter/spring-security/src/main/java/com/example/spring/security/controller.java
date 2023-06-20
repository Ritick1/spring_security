package com.example.spring.security;

import com.example.spring.security.Domain.User;
import com.example.spring.security.repository.LoginUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class controller {

    private final LoginUserRepository loginUserRepository;

    public controller(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }


    @GetMapping("/getHello")
    public String getHello(){
        return "hi spring security!";
    }
}
