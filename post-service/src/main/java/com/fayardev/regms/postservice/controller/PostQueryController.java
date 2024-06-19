package com.fayardev.regms.postservice.controller;

import com.fayardev.regms.postservice.dto.PostDto;
import com.fayardev.regms.postservice.entity.Post;
import com.fayardev.regms.postservice.service.PostQueryHandler;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryHandler handler;
    private final ModelMapper modelMapper;

    @QueryMapping
    public List<Post> getPosts() {
        return convertToDto(handler.getAll());
    }

    @QueryMapping
    public Post getPostById(@Argument String id) {
        return modelMapper.map(handler.get(id), Post.class);
    }

    private List<Post> convertToDto(List<PostDto> posts) {
        return posts.stream()
                .map(post -> modelMapper.map(post, Post.class))
                .collect(Collectors.toList());
    }
}
