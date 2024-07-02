package com.fayardev.regms.commentservice.controller;

import com.fayardev.regms.commentservice.dto.LikeDto;
import com.fayardev.regms.commentservice.service.LikeQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LikeQueryController {

    private final LikeQueryHandler handler;

    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> getLikesCount(@PathVariable String postId) {
        return ResponseEntity.ok(handler.getLikesCountForPost(postId));
    }

    @GetMapping("/users/{postId}")
    public ResponseEntity<List<String>> getUsersWhoLikedPost(@PathVariable String postId) {
        return ResponseEntity.ok(handler.getUsersWhoLikedPost(postId));
    }

    @GetMapping("/is-liked")
    public ResponseEntity<Boolean> isPostLikedByUser(@RequestParam String postId, @RequestParam String userId) {
        return ResponseEntity.ok(handler.isPostLikedByUser(postId, userId));
    }

    @GetMapping
    public Iterable<LikeDto> getAllUsers() {
        return handler.getAll();
    }
}
