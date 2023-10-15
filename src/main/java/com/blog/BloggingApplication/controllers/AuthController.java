package com.blog.BloggingApplication.controllers;

import com.blog.BloggingApplication.Config.CustomUserConfig;
import com.blog.BloggingApplication.constants.UrlConstants;
import com.blog.BloggingApplication.security.JwtHelper;
import com.blog.BloggingApplication.security.JwtRequest;
import com.blog.BloggingApplication.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("All")
public class AuthController {

    @Autowired
    CustomUserConfig customUserConfig;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHelper jwtHelper;

    @PostMapping(UrlConstants.GENERATE_TOKEN)
    public ResponseEntity<JwtResponse> getToken(@RequestBody JwtRequest jwtRequest){
        
        this.doAuthenticate(jwtRequest.getEmail(),jwtRequest.getPassword());
        UserDetails userDetails = customUserConfig.loadUserByUsername(jwtRequest.getEmail());
        String jwtToken = jwtHelper.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder().token(jwtToken).userName(jwtRequest.getEmail()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
        authenticationManager.authenticate(authentication);
    }
}
