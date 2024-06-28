package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.service.ProfileQueryHandler;
import com.fayardev.regms.profileservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProfileQueryController {

    private final ProfileQueryHandler handler;

    @GetMapping
    public ResponseEntity<ProfileDto> getMyProfile() {
        return ResponseEntity.ok(handler.get(JwtUtils.getUserUUID()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String username) {
        return ResponseEntity.ok(handler.getByUsername(username));
    }

    @GetMapping("/get")
    public ResponseEntity<ProfileDto> getProfileById(@RequestParam String id) {
        return ResponseEntity.ok(handler.get(id));
    }
}
