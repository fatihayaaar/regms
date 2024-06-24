package com.fayardev.regms.postservice.controller;

import com.fayardev.regms.postservice.dto.PostDto;
import com.fayardev.regms.postservice.service.PostQueryHandler;
import com.fayardev.regms.postservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryHandler handler;

    @QueryMapping
    public List<PostDto> getPosts() {
        return handler.getAll();
    }

    @QueryMapping
    public List<PostDto> getMyPosts() {
        return handler.getMyPosts(JwtUtils.getUserUUID());
    }

    @QueryMapping
    public PostDto getPostById(@Argument String id) {
        return handler.get(id);
    }

    @QueryMapping
    public List<PostDto> getPostsByUsername(@Argument String username) {
        return handler.getPostsByUsername(username);
    }
}
