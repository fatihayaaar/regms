package com.fayardev.regms.likeservice.service;

import com.fayardev.regms.likeservice.dto.LikeDto;
import com.fayardev.regms.likeservice.repository.LikeRepository;
import com.fayardev.regms.likeservice.service.abstracts.ILikeQueryHandler;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeQueryHandler implements ILikeQueryHandler<LikeDto> {

    private final LikeRepository repository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public long getLikesCountForPost(String postId) {
        return repository.countLikesByPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getUsersWhoLikedPost(String postId) {
        return repository.findUsersWhoLikedPost(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isPostLikedByUser(String postId, String userId) {
        return repository.isPostLikedByUser(postId, userId);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<LikeDto> getAll() {
        return repository.findAll().stream().map(u -> modelMapper.map(u, LikeDto.class)).toList();
    }
}
