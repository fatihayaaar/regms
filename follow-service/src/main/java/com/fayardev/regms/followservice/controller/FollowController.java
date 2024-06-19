package com.fayardev.regms.followservice.controller;

import com.fayardev.regms.followservice.dto.UserDto;
import com.fayardev.regms.followservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestParam UserDto userDto) {
        return ResponseEntity.ok(service.createUser(userDto));
    }

    @PostMapping("/{username}/follow")
    public void followUser(@PathVariable String username, @RequestParam String followUsername) {
        ResponseEntity.ok(service.followUser(username, followUsername));
    }

    @PostMapping("/{username}/unfollow")
    public ResponseEntity<Boolean> unfollowUser(@PathVariable String username, @RequestParam String unfollowUsername) {
        return ResponseEntity.ok(service.unfollowUser(username, unfollowUsername));
    }

    @GetMapping
    public Iterable<UserDto> getAllUsers() {
        return service.getAll();
    }
}
