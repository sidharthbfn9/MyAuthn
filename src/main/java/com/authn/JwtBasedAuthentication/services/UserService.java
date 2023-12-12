package com.authn.JwtBasedAuthentication.services;

import com.authn.JwtBasedAuthentication.entities.User;
import com.authn.JwtBasedAuthentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(){

    }

    public List<User> getUsers(Integer appId){
        Optional<List<User>> users = userRepository.findAllByAppId(appId);
        if(users.isPresent()) return users.get();
        else return new ArrayList<>();
    }

    public User createUser(User user) throws Exception {
        try{
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);}
        catch (Exception e){
            throw new Exception("User with email already exists!");
        }
    }
}
