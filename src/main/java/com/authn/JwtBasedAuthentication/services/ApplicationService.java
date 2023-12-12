package com.authn.JwtBasedAuthentication.services;

import com.authn.JwtBasedAuthentication.entities.App;
import com.authn.JwtBasedAuthentication.repositories.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationService {
    @Autowired
    private AppRepository appRepository;

    public App createApplication(App app){
        return appRepository.save(app);
    }
}
