package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(service.add(profileDto));
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyProfile() {
        return ResponseEntity.ok(service.get(UUID.randomUUID()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username) {
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
