package com.authn.JwtBasedAuthentication.controllers;

import com.authn.JwtBasedAuthentication.entities.User;
import com.authn.JwtBasedAuthentication.models.AuthenticateRequest;
import com.authn.JwtBasedAuthentication.models.JwtRequest;
import com.authn.JwtBasedAuthentication.models.JwtResponse;
import com.authn.JwtBasedAuthentication.security.JwtService;
import com.authn.JwtBasedAuthentication.services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        this.doAuthenticate(jwtRequest.getEmail()+":"+jwtRequest.getAppId(), jwtRequest.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getEmail()+":"+jwtRequest.getAppId());
        String token = this.jwtService.generateToken(userDetails, jwtRequest.getAppId());
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername())
                .appId(jwtRequest.getAppId()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<List<com.authn.JwtBasedAuthentication.entities.User>> admin(HttpServletRequest request, HttpServletResponse response) {
        String requestHeader = request.getHeader("Authorization");
        //logger.info(" Header :  {}", requestHeader);
        String username = null;
        Integer appId = null;
        String subject = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            token = requestHeader.substring(7);
            try {
                subject = this.jwtService.getClaimFromToken(token, Claims::getSubject);
                String split[] = subject.split(":");
                username = split[0];
                appId = Integer.parseInt(split[1]);
                List<com.authn.JwtBasedAuthentication.entities.User> users = this.userService.getUsers(appId);
                return new ResponseEntity<>(users, HttpStatus.OK);
            } catch (Exception e) {

            }
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }



    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticateRequest authenticateRequest) {
        try {
            User userDetails = (User) this.userDetailsService.loadUserByUsername(authenticateRequest.getEmailId()+":"+authenticateRequest.getAppId());
            if (this.jwtService.validateToken(authenticateRequest.getToken(), userDetails)) {
                return new ResponseEntity<>(userDetails, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Invalid token", HttpStatus.OK);
    }
    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


}
