package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.service.ProfileService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }
}
