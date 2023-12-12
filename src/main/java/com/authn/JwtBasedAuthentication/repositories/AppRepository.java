package com.authn.JwtBasedAuthentication.repositories;

import com.authn.JwtBasedAuthentication.entities.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, String> {

}
