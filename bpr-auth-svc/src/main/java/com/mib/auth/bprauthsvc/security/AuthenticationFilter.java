package com.mib.auth.bprauthsvc.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mib.auth.bprauthsvc.dto.UserDto;
import com.mib.auth.bprauthsvc.dto.request.LoginRequestModel;
import com.mib.auth.bprauthsvc.services.UserServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserServices usersServices;
    private Environment environment;

    public AuthenticationFilter(UserServices usersServices, Environment environment,
                                AuthenticationManager authenticationManager) {
        this.usersServices = usersServices;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {

            LoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String userName = ((User) auth.getPrincipal()).getUsername();

        UserDto userDetails = usersServices.getUserDetailsByUsername(userName);

        System.out.println(":::::: " + userDetails.getScope());

        Date expireIn = new Date(
                System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time")));

        String token = Jwts.builder().setSubject(userDetails.getUserId())
                .claim("SCOPE", userDetails.getScope())
                .setExpiration(expireIn)
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();


        res.addHeader("token", token);
        res.addHeader("expire_in", environment.getProperty("token.expiration_time").toString());
        res.addHeader("userId", userDetails.getUserId());

    }
}
