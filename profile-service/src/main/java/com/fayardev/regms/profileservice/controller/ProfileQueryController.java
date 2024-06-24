package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.service.ProfileQueryHandler;
import com.fayardev.regms.profileservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
