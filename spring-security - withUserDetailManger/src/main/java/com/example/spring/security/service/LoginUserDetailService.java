package com.example.spring.security.service;

import com.arangodb.entity.DocumentEntity;
import com.arangodb.springframework.core.ArangoOperations;
import com.arangodb.springframework.core.convert.ArangoConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailService implements UserDetailsManager {
    private ArangoOperations arangoOperations;

    private ArangoConverter arangoConverter;

    public LoginUserDetailService(ArangoOperations arangoOperations, ArangoConverter arangoConverter) {
        this.arangoOperations = arangoOperations;
        this.arangoConverter = arangoConverter;
    }

    @Override
    public void createUser(UserDetails user) {
        DocumentEntity insert = arangoOperations.insert(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


}
