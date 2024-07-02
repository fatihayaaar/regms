package com.fayardev.regms.commentservice.controller;

import com.fayardev.regms.commentservice.dto.CommentDto;
import com.fayardev.regms.commentservice.service.CommentQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CommentQueryController {

    private final CommentQueryHandler handler;

    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> getLikesCount(@PathVariable String postId) {
        return ResponseEntity.ok(handler.getCommentCountForPost(postId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable String postId) {
        return ResponseEntity.ok(handler.getCommentsByPostId(postId));
    }
    @GetMapping
    public Iterable<CommentDto> getAllUsers() {
        return handler.getAll();
    }
}
