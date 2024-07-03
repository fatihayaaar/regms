package com.fayardev.regms.commentservice.service;

import com.fayardev.regms.commentservice.client.UserGrpcClient;
import com.fayardev.regms.commentservice.dto.CommentDto;
import com.fayardev.regms.commentservice.repository.CommentRepository;
import com.fayardev.regms.commentservice.service.abstracts.ICommentQueryHandler;
import com.fayardev.userservice.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentQueryHandler implements ICommentQueryHandler<CommentDto> {

    private final CommentRepository repository;
    private final UserGrpcClient userGrpcClient;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public long getCommentCountForPost(String postId) {
        return repository.countCommentsByPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByPostId(String postId) {
        return repository.findCommentsByPostId(postId).stream().map((c) -> {
            CommentDto comment = modelMapper.map(c, CommentDto.class);

            UserResponse user = userGrpcClient.getUserByUuid(comment.getUserId());
            comment.setAvatar(user.getAvatar());
            comment.setUsername(user.getUid());

            return comment;
        }).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<CommentDto> getAll() {
        return repository.findAll().stream().map(u -> modelMapper.map(u, CommentDto.class)).toList();
    }
}
