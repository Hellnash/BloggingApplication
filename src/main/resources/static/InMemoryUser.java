//package com.blog.BloggingApplication.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@SuppressWarnings("unused")
//public class InMemoryUser {
//
//    /* When we add a dependency of spring security all the apis becomes secure by default
//     * from a random password generated in console and 'user' as username in order to override
//     * the default settings we can create this bean and manually set some usernames and passwords
//     */
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//    UserDetails firstUser = User.builder().username("shubham")
//                                          .password(passwordEncoder().encode("lovely"))
//                                          .roles("Admin").build();
//
//    UserDetails secondUser = User.builder().username("lovely")
//                                           .password(passwordEncoder().encode("shubham"))
//                                           .roles("User").build();
//
//        return new InMemoryUserDetailsManager(firstUser,secondUser);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
