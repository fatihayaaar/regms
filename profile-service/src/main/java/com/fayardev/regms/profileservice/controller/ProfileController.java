package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.service.ProfileService;
import com.fayardev.regms.profileservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @PostMapping("/add")
    public ResponseEntity<ProfileDto> add(@RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(service.add(profileDto));
    }

    @GetMapping
    public ResponseEntity<ProfileDto> getMyProfile() {
        return ResponseEntity.ok(service.get(JwtUtils.getUserUUID()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String username) {
        return ResponseEntity.ok(service.get(username));
    }

    @PutMapping("/change-biography")
    public ResponseEntity<Boolean> changeBiography(@RequestBody ProfileDto profileDto) {
        profileDto.setUserId(JwtUtils.getUserUUID());
        service.changeBiography(profileDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/change-is-private")
    public ResponseEntity<Boolean> changeIsPrivate(@RequestBody ProfileDto profileDto) {
        profileDto.setUserId(JwtUtils.getUserUUID());
        service.changeIsPrivate(profileDto);
        return ResponseEntity.ok(true);
    }
}
