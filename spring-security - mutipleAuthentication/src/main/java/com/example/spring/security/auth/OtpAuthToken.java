package com.example.spring.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OtpAuthToken extends UsernamePasswordAuthenticationToken {

    public OtpAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public OtpAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
