package com.example.spring.security.configuration.security;

import com.example.spring.security.service.LoginUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebConfiguration  {

    private final LoginUserDetailService loginUserDetailService;

    public WebConfiguration(LoginUserDetailService loginUserDetailService) {
        this.loginUserDetailService = loginUserDetailService;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("**/*.graphql")
//                .permitAll();
//
//        http.csrf().disable();
//        http.cors().configurationSource(corsConfigurationSource());
//    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedMethods(List.of("*"));
//        configuration.setAllowedHeaders(List.of("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


//    @Bean
//    public UserDetailsService userDetailsService(){
//        return loginUserDetailService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetails = new InMemoryUserDetailsManager();
//        UserDetails ritik = User.withUsername("ritik").password("123").authorities("read").build();
//        userDetails.createUser(ritik);
//        return userDetails;
//    }

}
