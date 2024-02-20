package com.fayardev.regms.postservice.service;

import com.fayardev.regms.postservice.dto.PostDto;
import com.fayardev.regms.postservice.entity.Post;
import com.fayardev.regms.postservice.entity.content.Content;
import com.fayardev.regms.postservice.repository.PostRepository;
import com.fayardev.regms.postservice.service.abstracts.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService<PostDto> {

    private final PostRepository repository;
    private final ModelMapper modelMapper;

    public PostService(PostRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto add(PostDto postDTO) {
        Post post = repository.save(modelMapper.map(postDTO, Post.class));
        postDTO.setId(post.getId());
        return postDTO;
    }

    @Override
    public void delete(UUID uuid) {
        Post post = repository.findById(uuid).orElseThrow(IllegalArgumentException::new);
        repository.delete(post);
    }

    @Override
    public PostDto update(PostDto postDTO) {
        Optional<Post> post = repository.findById(postDTO.getId());
        Post updatedPost = post.map(it -> {
            it.setContent(modelMapper.map(postDTO.getContent(), Content.class));
            it.setVisible(postDTO.isVisible());
            it.setUpdatedDate(new Date());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedPost);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto visibility(UUID uuid, boolean visible) {
        Optional<Post> post = repository.findById(uuid);
        Post updatedPost = post.map(it -> {
            it.setVisible(visible);
            it.setUpdatedDate(new Date());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedPost);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    public PostDto get(UUID uuid) {
        return modelMapper.map(repository.findById(uuid).orElseThrow(IllegalAccessError::new), PostDto.class);
    }

    public Slice<PostDto> getAll(Pageable pageable) {
        Slice<Post> posts = repository.findAll(pageable);
        List<PostDto> postDTOs = posts
                .getContent()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return new SliceImpl<>(postDTOs, pageable, posts.hasNext());
    }
}
