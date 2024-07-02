package com.fayardev.regms.commentservice.controller;

import com.fayardev.regms.commentservice.dto.CommentDto;
import com.fayardev.regms.commentservice.service.CommentCommandHandler;
import com.fayardev.regms.commentservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CommentCommandController {

    private final CommentCommandHandler handler;

    @PostMapping
    public ResponseEntity<Long> comment(@RequestBody CommentDto comment) {
        comment.setUserId(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.addComment(comment));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> unlike(@RequestBody CommentDto comment) {
        comment.setUserId(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.removeComment(comment));
    }
}
