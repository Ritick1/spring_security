package com.example.spring.security.configuration.security;

import com.example.spring.security.filter.MyBasicAuthenticationFilter;
import com.example.spring.security.provider.MyAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private final MyBasicAuthenticationFilter myBasicAuthenticationFilter;

    private final MyAuthenticationProvider myAuthenticationProvider;

    public WebConfiguration(MyBasicAuthenticationFilter myBasicAuthenticationFilter, MyAuthenticationProvider myAuthenticationProvider) {
        this.myBasicAuthenticationFilter = myBasicAuthenticationFilter;
        this.myAuthenticationProvider = myAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest()
                .permitAll();
        http.addFilterAt(myBasicAuthenticationFilter, BasicAuthenticationFilter.class);
//        http.authorizeRequests().anyRequest().permitAll();

        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(myAuthenticationProvider);
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
