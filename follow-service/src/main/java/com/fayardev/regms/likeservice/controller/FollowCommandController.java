package com.fayardev.regms.likeservice.controller;

import com.fayardev.regms.likeservice.dto.UserDto;
import com.fayardev.regms.likeservice.service.UserCommandHandler;
import com.fayardev.regms.likeservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FollowCommandController {

    private final UserCommandHandler handler;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(handler.createUser(userDto));
    }

    @PostMapping("/{username}/follow")
    public void followUser(@PathVariable String username) {
        handler.followUser(username, JwtUtils.getUserUUID());
    }

    @PostMapping("/{username}/unfollow")
    public void unfollowUser(@PathVariable String username) {
        handler.unfollowUser(username, JwtUtils.getUserUUID());
    }

    @GetMapping("{username}/is-following")
    public ResponseEntity<Boolean> isFollowing(@PathVariable String username) {
        return ResponseEntity.ok(handler.isFollowing(JwtUtils.getUserUUID(), username));
    }
}
