package com.example.spring.security.Domain;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Document("user")
public class User {
    @ArangoId
    private String arangoId;
    @Id
    private String id;
    private String username;
    private String password;



}
