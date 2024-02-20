package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.service.ProfileService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyProfile() {
        return ResponseEntity.ok(service.get(UUID.randomUUID()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getProfile(@PathVariable @NotNull String username) {
        return ResponseEntity.ok(service.get(UUID.randomUUID()));
    }

    @PutMapping("/change-biography")
    public ResponseEntity<?> changeBiography(@RequestBody String biography) {
        service.changeBiography(UUID.randomUUID(), biography);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/change-is-private")
    public ResponseEntity<?> changeIsPrivate(@RequestBody boolean isPrivate) {
        service.changeIsPrivate(UUID.randomUUID(), isPrivate);
        return ResponseEntity.ok(true);
    }
}
