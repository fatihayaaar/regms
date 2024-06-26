package com.fayardev.regms.followservice.controller;

import com.fayardev.regms.followservice.dto.UserDto;
import com.fayardev.regms.followservice.service.UserQueryHandler;
import com.fayardev.regms.followservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FollowQueryController {

    private final UserQueryHandler handler;

    @GetMapping("/followee-count")
    public ResponseEntity<Integer> getFolloweeCount(@RequestParam String username) {
        return ResponseEntity.ok(handler.getFolloweeCount(username));
    }

    @GetMapping("/follower-count")
    public ResponseEntity<Integer> getFollowerCount(@RequestParam String username) {
        return ResponseEntity.ok(handler.getFollowerCount(username));
    }

    @GetMapping("/followees")
    public ResponseEntity<List<UserDto>> getFollowees(@RequestParam String username) {
        return ResponseEntity.ok(handler.getFollowees(username));
    }

    @GetMapping("/followers")
    public ResponseEntity<List<UserDto>> getFollowers(@RequestParam String username) {
        return ResponseEntity.ok(handler.getFollowers(username));
    }

    @GetMapping("/my-followee-count")
    public ResponseEntity<Integer> getMyFolloweeCount() {
        return ResponseEntity.ok(handler.getFolloweeCountById(JwtUtils.getUserUUID()));
    }

    @GetMapping("/my-follower-count")
    public ResponseEntity<Integer> getMyFollowerCount() {
        return ResponseEntity.ok(handler.getFollowerCountById(JwtUtils.getUserUUID()));
    }

    @GetMapping("/my-followees")
    public ResponseEntity<List<UserDto>> getMyFollowees() {
        return ResponseEntity.ok(handler.getFolloweesById(JwtUtils.getUserUUID()));
    }

    @GetMapping("/my-followers")
    public ResponseEntity<List<UserDto>> getMyFollowers() {
        return ResponseEntity.ok(handler.getFollowersById(JwtUtils.getUserUUID()));
    }

    @GetMapping
    public Iterable<UserDto> getAllUsers() {
        return handler.getAll();
    }
}
