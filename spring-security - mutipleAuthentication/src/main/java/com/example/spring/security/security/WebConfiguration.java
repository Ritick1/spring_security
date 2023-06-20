package com.example.spring.security.security;

import com.example.spring.security.filter.UserPasswordAuthFilter;
import com.example.spring.security.provider.OtpAuthProvider;
import com.example.spring.security.provider.UserPasswordAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private final UserPasswordAuthFilter userPasswordAuthFilter;

    private final UserPasswordAuthProvider userPasswordAuthProvider;

    private final OtpAuthProvider otpAuthProvider;

    public WebConfiguration(UserPasswordAuthFilter userPasswordAuthFilter, UserPasswordAuthProvider userPasswordAuthProvider, OtpAuthProvider otpAuthProvider) {
        this.userPasswordAuthFilter = userPasswordAuthFilter;
        this.userPasswordAuthProvider = userPasswordAuthProvider;
        this.otpAuthProvider = otpAuthProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userPasswordAuthProvider).authenticationProvider(otpAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .permitAll();
        http.addFilterAt(userPasswordAuthFilter, BasicAuthenticationFilter.class);

        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
    }


}
