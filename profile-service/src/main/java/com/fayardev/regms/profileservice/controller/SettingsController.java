package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.service.SettingsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsController {

    private final SettingsService service;

    public SettingsController(SettingsService service) {
        this.service = service;
    }
}
