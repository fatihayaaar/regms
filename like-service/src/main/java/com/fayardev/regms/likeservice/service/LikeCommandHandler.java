package com.fayardev.regms.likeservice.service;

import com.fayardev.regms.likeservice.client.PostGrpcClient;
import com.fayardev.regms.likeservice.dto.LikeDto;
import com.fayardev.regms.likeservice.entity.Like;
import com.fayardev.regms.likeservice.repository.LikeRepository;
import com.fayardev.regms.likeservice.service.abstracts.ILikeCommandHandler;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LikeCommandHandler implements ILikeCommandHandler<LikeDto> {

    private final LikeRepository repository;
    private final PostGrpcClient postGrpcClient;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public Long addLike(LikeDto like) {
        if (this.postGrpcClient.checkPostExists(like.getPost().getId())) {
            if (!repository.isPostLikedByUser(like.getPost().getId(), like.getUserId())) {
                like.setCreatedDate(new Date());
                return repository.save(modelMapper.map(like, Like.class)).getId();
            }
        }
        throw new IllegalArgumentException();
    }

    @Transactional
    @Override
    public boolean removeLike(LikeDto like) {
        if (repository.isPostLikedByUser(like.getPost().getId(), like.getUserId())) {
            repository.deleteByPostIdAndUserId(like.getPost().getId(), like.getUserId());
            return true;
        }
        return false;
    }

}
