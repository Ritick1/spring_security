package com.example.spring.security.configuration.database;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.config.ArangoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfiguration implements ArangoConfiguration {

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder().host("localhost",8529).user("root").password("root");
    }

    @Override
    public String database() {
        return "security";
    }
}
