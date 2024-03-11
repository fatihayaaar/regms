package com.fayardev.regms.followservice.service;

import com.fayardev.regms.followservice.dto.FollowDto;
import com.fayardev.regms.followservice.entity.Follow;
import com.fayardev.regms.followservice.repository.FollowRepository;
import com.fayardev.regms.followservice.service.abstracts.IFollowService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowService implements IFollowService<FollowDto> {

    private final FollowRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public FollowDto add(FollowDto entity) {
        Follow follow = repository.save(modelMapper.map(entity, Follow.class));
        entity.setId(follow.getId());
        return entity;
    }

    @Override
    public void delete(UUID id) {
        Follow follow = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        repository.delete(follow);
    }

    @Override
    public FollowDto update(FollowDto entity) {
        Optional<Follow> post = repository.findById(entity.getId());
        Follow updatedFollow = post.map(it -> {
            it.setFollowerId(entity.getFollowerId());
            it.setFollowingId(entity.getFollowingId());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedFollow);
        return modelMapper.map(updatedFollow, FollowDto.class);
    }

    @Override
    public void follow(UUID followerID, UUID followingID) {
        Follow follow = repository
                .getFollowByFollowerIdAndFollowingId(followerID, followingID)
                .orElseThrow(NullPointerException::new);

        if (follow == null) {
            FollowDto followDto = FollowDto
                    .builder()
                    .followerId(followerID)
                    .followingId(followingID)
                    .build();
            add(followDto);
        }
    }

    @Override
    public void unFollow(UUID followerID, UUID followingID) {
        repository
                .getFollowByFollowerIdAndFollowingId(followerID, followingID)
                .ifPresent(follow -> delete(follow.getId()));
    }

    @Override
    public FollowDto get(UUID id) {
        return modelMapper.map(repository.findById(id).orElseThrow(IllegalAccessError::new), FollowDto.class);
    }

    @Override
    public Slice<FollowDto> getAll(Pageable pageable) {
        return null;
    }
}
