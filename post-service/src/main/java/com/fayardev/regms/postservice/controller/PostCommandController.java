package com.fayardev.regms.postservice.controller;

import com.fayardev.regms.postservice.dto.PostDto;
import com.fayardev.regms.postservice.entity.Post;
import com.fayardev.regms.postservice.service.PostCommandHandler;
import com.fayardev.regms.postservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostCommandController {

    private final PostCommandHandler handler;
    private final ModelMapper modelMapper;

    @MutationMapping
    public Post createPost(@Argument PostDto post) {
        post.setUserId(JwtUtils.getUserUUID());
        return modelMapper.map(handler.add(post), Post.class);
    }

    @MutationMapping
    public boolean delete(@Argument PostDto post) {
        post.setUserId(JwtUtils.getUserUUID());
        return handler.delete(post);
    }

    @MutationMapping
    public Post update(@Argument PostDto post) {
        return modelMapper.map(handler.update(post), Post.class);
    }
}
