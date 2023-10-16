package com.blog.BloggingApplication.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@SuppressWarnings("All")
public class JwtEntryPoint implements AuthenticationEntryPoint {

    //run when an unauthenticated user will try to login the API
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
                         throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = response.getWriter();
            writer.println("Access Denied !! "+authException.getMessage());
        }
}