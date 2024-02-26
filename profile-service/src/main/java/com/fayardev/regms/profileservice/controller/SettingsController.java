package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.service.SettingsService;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.SmartLifecycle;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService service;

    public SettingsController(SettingsService service) {
        this.service = service;
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMySettings() {
        return ResponseEntity.ok(service.get(UUID.randomUUID()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getSettings(@PathVariable @NotNull String username) {
        return ResponseEntity.ok(service.get(UUID.randomUUID()));
    }

    @PutMapping("/change-notification-enabled")
    public ResponseEntity<?> changeNotificationsEnabled(@RequestBody boolean notificationEnabled) {
        service.changeNotificationsEnabled(UUID.randomUUID(), notificationEnabled);
        return ResponseEntity.ok(true);
    }
}
