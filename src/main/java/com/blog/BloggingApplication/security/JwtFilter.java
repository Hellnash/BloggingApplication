package com.blog.BloggingApplication.security;

import com.blog.BloggingApplication.config.CustomUserConfig;
import com.blog.BloggingApplication.entities.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@SuppressWarnings("All")
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    CustomUserConfig customUserConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //extract jwt token from request header removing Bearer string
        String requestHeader = request.getHeader("Authorization");
        log.info(" Header {} ",requestHeader);
        String token = null;
        if(requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);
        }
        else{
            log.error("Invalid Header Value !!");
        }

        //got the username using jwt helper from token
        String userName = jwtHelper.getUsernameFromToken(token);
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //got users as userdetails from database using username
            Users users = (Users) customUserConfig.loadUserByUsername(userName);
            //vaildate token user details with database user details
            Boolean validateToken = jwtHelper.validateToken(token, users);
            if(validateToken){
                //set authentication in security context holder
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(users, null, users.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else{
                log.error("Validation fails !!!");
            }
        }
        //filter the request to access APIs
        filterChain.doFilter(request,response);
    }
}
