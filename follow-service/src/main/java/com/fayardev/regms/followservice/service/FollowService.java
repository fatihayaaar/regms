package com.fayardev.regms.followservice.service;

import com.fayardev.regms.followservice.dto.FollowDto;
import com.fayardev.regms.followservice.entity.Follow;
import com.fayardev.regms.followservice.repository.FollowRepository;
import com.fayardev.regms.followservice.service.abstracts.IFollowService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FollowService implements IFollowService<FollowDto> {

    private final FollowRepository repository;
    private final ModelMapper modelMapper;

    public FollowService(FollowRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FollowDto add(FollowDto entity) {
        Follow follow = repository.save(modelMapper.map(entity, Follow.class));
        entity.setUuid(follow.getUuid());
        return entity;
    }

    @Override
    public void delete(UUID uuid) {
        Follow follow = repository.findById(uuid).orElseThrow(IllegalArgumentException::new);
        repository.delete(follow);
    }

    @Override
    public FollowDto update(FollowDto entity) {
        Optional<Follow> post = repository.findById(entity.getUuid());
        Follow updatedFollow = post.map(it -> {
            it.setFollowerId(entity.getFollowerId());
            it.setFollowingId(entity.getFollowingId());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedFollow);
        return modelMapper.map(updatedFollow, FollowDto.class);
    }

    @Override
    public FollowDto get(UUID uuid) {
        return modelMapper.map(repository.findById(uuid).orElseThrow(IllegalAccessError::new), FollowDto.class);
    }

    @Override
    public Slice<FollowDto> getAll(Pageable pageable) {
        return null;
    }
}
