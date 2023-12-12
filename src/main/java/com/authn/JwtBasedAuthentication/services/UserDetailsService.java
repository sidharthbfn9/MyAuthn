package com.authn.JwtBasedAuthentication.services;

import com.authn.JwtBasedAuthentication.entities.User;
import com.authn.JwtBasedAuthentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // load user from database
        String[] split = username.split(":");
        User user = userRepository.findByEmailAndAppId(split[0], Integer.parseInt(split[1])).orElseThrow(() -> new RuntimeException());
        return user;
    }
}
