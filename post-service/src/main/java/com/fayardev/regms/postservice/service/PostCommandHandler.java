package com.fayardev.regms.postservice.service;

import com.fayardev.regms.postservice.dto.PostDto;
import com.fayardev.regms.postservice.entity.Post;
import com.fayardev.regms.postservice.repository.PostRepository;
import com.fayardev.regms.postservice.service.abstracts.IPostCommandHandler;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostCommandHandler implements IPostCommandHandler<PostDto> {

    private final PostRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto add(PostDto postDTO) {
        postDTO.setId(UUID.randomUUID().toString());
        postDTO.setDeleted(false);
        postDTO.setCreatedDate(new Date());
        repository.save(modelMapper.map(postDTO, Post.class));
        return postDTO;
    }

    @Override
    public boolean delete(String id) {
        Post post = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        post.setDeleted(true);
        update(modelMapper.map(post, PostDto.class));
        return true;
    }

    @Override
    public PostDto update(PostDto postDTO) {
        Optional<Post> post = repository.findById(postDTO.getId());
        Post updatedPost = post.map(it -> {
            it.setVisible(postDTO.isVisible());
            it.setUpdatedDate(new Date());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedPost);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto visibility(String id, boolean visible) {
        Optional<Post> post = repository.findById(id);
        Post updatedPost = post.map(it -> {
            it.setVisible(visible);
            it.setUpdatedDate(new Date());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedPost);
        return modelMapper.map(updatedPost, PostDto.class);
    }
}
