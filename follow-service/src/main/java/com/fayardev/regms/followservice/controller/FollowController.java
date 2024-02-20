package com.fayardev.regms.followservice.controller;

import com.fayardev.regms.followservice.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FollowController {

    private final FollowService service;

    public FollowController(FollowService service) {
        this.service = service;
    }

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@RequestBody String username) {
        service.follow(UUID.randomUUID(), UUID.randomUUID());
        return ResponseEntity.ok(true);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollow(@RequestBody String username) {
        service.unFollow(UUID.randomUUID(), UUID.randomUUID());
        return ResponseEntity.ok(true);
    }
}
