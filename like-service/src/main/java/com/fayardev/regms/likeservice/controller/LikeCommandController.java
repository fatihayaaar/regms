package com.fayardev.regms.likeservice.controller;

import com.fayardev.regms.likeservice.dto.LikeDto;
import com.fayardev.regms.likeservice.entity.Post;
import com.fayardev.regms.likeservice.service.LikeCommandHandler;
import com.fayardev.regms.likeservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LikeCommandController {

    private final LikeCommandHandler handler;

    @PostMapping("/like/{postId}")
    public ResponseEntity<Long> like(@PathVariable String postId) {
        LikeDto like = new LikeDto();
        like.setPost(Post.builder().id(postId).build());
        like.setUserId(JwtUtils.getUserUUID());

        return ResponseEntity.ok(handler.addLike(like));
    }

    @PostMapping("/unlike/{postId}")
    public ResponseEntity<Boolean> unlike(@PathVariable String postId) {
        LikeDto like = new LikeDto();
        like.setPost(Post.builder().id(postId).build());
        like.setUserId(JwtUtils.getUserUUID());

        return ResponseEntity.ok(handler.removeLike(like));
    }
}
