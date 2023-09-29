package com.blog.BloggingApplication.config;

import com.blog.BloggingApplication.constants.UrlConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@SuppressWarnings("unused")
public class SecurityConfiguration {
	
	/*@Autowired
	CustomUserDetails customUsers;*/
	
	@Bean
	public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, UrlConstants.TOKEN).permitAll()
                .anyRequest().authenticated());
        return http.build();
	}
	
//	@Bean
//	public AuthenticationManager authManager(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.customUsers).passwordEncoder(passwordEncoder());
//		return auth.build();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
