package com.fayardev.regms.profileservice.controller;

import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.service.ProfileCommandHandler;
import com.fayardev.regms.profileservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProfileCommandController {

    private final ProfileCommandHandler handler;

    @PostMapping("/feign/add")
    public ResponseEntity<ProfileDto> add(@RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(handler.add(profileDto));
    }

    @PutMapping("/change-biography")
    public ResponseEntity<Boolean> changeBiography(@RequestBody ProfileDto profileDto) {
        profileDto.setUserId(JwtUtils.getUserUUID());
        handler.changeBiography(profileDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/change-is-private")
    public ResponseEntity<Boolean> changeIsPrivate(@RequestBody ProfileDto profileDto) {
        profileDto.setUserId(JwtUtils.getUserUUID());
        handler.changeIsPrivate(profileDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/change-background-image")
    public ResponseEntity<Boolean> changeBackgroundImage(@RequestBody ProfileDto profileDto) {
        profileDto.setUserId(JwtUtils.getUserUUID());
        handler.changeBackgroundImage(profileDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/delete-background-image")
    public ResponseEntity<Boolean> deleteBackgroundImage() {
        handler.deleteBackgroundImage(ProfileDto.builder().userId(JwtUtils.getUserUUID()).build());
        return ResponseEntity.ok(true);
    }
}
