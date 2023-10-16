package com.blog.BloggingApplication.Config;

import com.blog.BloggingApplication.constants.UrlConstants;
import com.blog.BloggingApplication.security.JwtAuthenticationEntryPoint;
import com.blog.BloggingApplication.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@SuppressWarnings("All")
public class SecurityConfig {

    @Autowired
    JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable) //csrf -> csrf.disable()
            .cors(AbstractHttpConfigurer::disable) //cors -> cors.disable()
            .authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").authenticated() //api URLs
                                               .requestMatchers(UrlConstants.GENERATE_TOKEN).permitAll() //jwt token api
                                               .anyRequest().authenticated()) //Http method
            .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //the request will go through authentication filter befor accessing the APIs
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
