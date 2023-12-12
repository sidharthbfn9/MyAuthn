package com.authn.JwtBasedAuthentication.controllers;

import com.authn.JwtBasedAuthentication.entities.App;
import com.authn.JwtBasedAuthentication.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@CrossOrigin
public class AppController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/createApp")
    public ResponseEntity<Object> createApplication(@RequestBody App app) {
        try {
            App ap = applicationService.createApplication(app);
            return new ResponseEntity<>(ap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
