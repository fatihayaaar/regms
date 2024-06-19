package com.fayardev.regms.postservice.service;

import com.fayardev.regms.postservice.dto.PostDto;
import com.fayardev.regms.postservice.entity.Post;
import com.fayardev.regms.postservice.repository.PostRepository;
import com.fayardev.regms.postservice.service.abstracts.IPostQueryHandler;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostQueryHandler implements IPostQueryHandler<PostDto> {

    private final PostRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto get(String id) {
        return modelMapper.map(repository.findById(id).orElseThrow(IllegalAccessError::new), PostDto.class);
    }

    @Override
    public List<PostDto> getAll() {
        Slice<Post> posts = repository.findAll(PageRequest.of(0, 10));

        return posts.getContent()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
