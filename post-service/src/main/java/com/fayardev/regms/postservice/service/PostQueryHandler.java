package com.fayardev.regms.postservice.service;

import com.fayardev.regms.postservice.client.UserGrpcClient;
import com.fayardev.regms.postservice.dto.PostDto;
import com.fayardev.regms.postservice.entity.Post;
import com.fayardev.regms.postservice.repository.PostRepository;
import com.fayardev.regms.postservice.service.abstracts.IPostQueryHandler;
import com.fayardev.userservice.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostQueryHandler implements IPostQueryHandler<PostDto> {

    private final PostRepository repository;
    private final UserGrpcClient userClient;
    private final ModelMapper modelMapper;

    @Override
    public PostDto get(String id) {
        Post post = repository.findById(id).orElseThrow(IllegalAccessError::new);
        UserResponse user = userClient.getUserByUuid(post.getUserId());

        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setUsername(user.getUid());
        postDto.setAvatar(user.getAvatar());

        return postDto;
    }

    @Override
    public List<PostDto> getMyPosts(String id) {
        Slice<Post> posts = repository.getPostsByUserId(id, PageRequest.of(0, 10));
        UserResponse user = userClient.getUserByUuid(id);

        return posts.getContent().stream().map(post -> {
            PostDto postDto = modelMapper.map(post, PostDto.class);
            postDto.setUsername(user.getUid());
            postDto.setAvatar(user.getAvatar());
            return postDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUsername(String username) {
        UserResponse user = userClient.getUserByUsername(username);

        Slice<Post> posts = repository.getPostsByUserId(user.getUuid(), PageRequest.of(0, 10));
        return posts.getContent().stream().map(post -> {
            PostDto postDto = modelMapper.map(post, PostDto.class);
            postDto.setUsername(user.getUid());
            postDto.setAvatar(user.getAvatar());
            return postDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAll() {
        Slice<Post> posts = repository.findAll(PageRequest.of(0, 10));

        return posts.getContent().stream().map(post -> {
            PostDto postDto = modelMapper.map(post, PostDto.class);
            UserResponse user = userClient.getUserByUuid(post.getUserId());
            postDto.setUsername(user.getUid());
            postDto.setAvatar(user.getAvatar());
            return postDto;
        }).collect(Collectors.toList());
    }
}
