package com.blog.BloggingApplication.Config;

import com.blog.BloggingApplication.entities.Users;
import com.blog.BloggingApplication.exceptions.UserNotFoundException;
import com.blog.BloggingApplication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@SuppressWarnings("unused")
public class CustomUserConfig implements UserDetailsService {

    /* The 1st way of overriding the default is creating a bean of UserDetailService
     * which overrides a method called loadUserByUsername that returns a user object
     * by passing a username as a parameter here we are manually overriding the same
     * method and returning a user object by setting its fields with details obtained
     * from our custom user database.
     */

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepo.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User", "userId" + username, 0));
        return User.builder().username(users.getEmail()).password(passwordEncoder().encode(users.getPassword())).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
