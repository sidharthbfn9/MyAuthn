package com.authn.JwtBasedAuthentication.repositories;

import com.authn.JwtBasedAuthentication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByEmailAndAppId(String email, Integer appId) throws UsernameNotFoundException;
    public Optional<List<User>> findAllByAppId(Integer appId);
}
