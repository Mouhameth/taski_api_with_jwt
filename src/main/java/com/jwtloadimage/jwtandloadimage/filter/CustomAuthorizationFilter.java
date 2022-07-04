package com.jwtloadimage.jwtandloadimage.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With, Content-Type,"+
                " Access-Control-Request-Methode,"
                +"Access-Control-Request-Headers, access_token, refresh_token, Authorization");
        response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin," +
                " Access-Control-Allow-Credentials, access_token, refresh_token, Authorization");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/user/token/refresh")){
           filterChain.doFilter(request,response);
       }
       else {
           String authorizationHeader = request.getHeader(AUTHORIZATION);
           if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
               try {
                   String token = authorizationHeader.substring("Bearer ".length());
                   Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                   JWTVerifier verifier = JWT.require(algorithm).build();
                   DecodedJWT decodedJWT = verifier.verify(token);
                   String username = decodedJWT.getSubject();
                   String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                   Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                   stream(roles).forEach(role->{
                        authorities.add(new SimpleGrantedAuthority(role));
                   });
                   UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                   filterChain.doFilter(request,response);
               }
               catch (Exception e){
                  log.error("Error logging in: {}",e.getMessage());
                  response.setHeader("error",e.getMessage());
                  response.sendError(FORBIDDEN.value());
                  response.setStatus(FORBIDDEN.value());
               }
           }
           else {
               filterChain.doFilter(request,response);
           }
       }

        }

    }
}
