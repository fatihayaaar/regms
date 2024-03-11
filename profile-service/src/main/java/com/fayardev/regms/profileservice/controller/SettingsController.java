package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService service;

    @GetMapping("/my")
    public ResponseEntity<?> getMySettings() {
        return ResponseEntity.ok(service.get(UUID.randomUUID()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getSettings(@PathVariable String username) {
        return ResponseEntity.ok(service.get(UUID.randomUUID()));
    }

    @PutMapping("/change-notification-enabled")
    public ResponseEntity<?> changeNotificationsEnabled(@RequestBody boolean notificationEnabled) {
        service.changeNotificationsEnabled(UUID.randomUUID(), notificationEnabled);
        return ResponseEntity.ok(true);
    }
}
