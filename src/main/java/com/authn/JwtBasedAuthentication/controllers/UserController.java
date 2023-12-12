package com.authn.JwtBasedAuthentication.controllers;

import com.authn.JwtBasedAuthentication.entities.User;
import com.authn.JwtBasedAuthentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody User user) throws Exception {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
        }catch (Exception r){
            return new ResponseEntity<>("User Already Exists", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/currentUser")
    public String getPrincipal(Principal principal){
        return principal.getName();
    }
}


