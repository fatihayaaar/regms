package com.fayardev.regms.commentservice.service;

import com.fayardev.regms.commentservice.client.PostGrpcClient;
import com.fayardev.regms.commentservice.dto.CommentDto;
import com.fayardev.regms.commentservice.entity.Comment;
import com.fayardev.regms.commentservice.repository.CommentRepository;
import com.fayardev.regms.commentservice.service.abstracts.ICommentCommandHandler;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentCommandHandler implements ICommentCommandHandler<CommentDto> {

    private final CommentRepository repository;
    private final PostGrpcClient postGrpcClient;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public CommentDto addComment(CommentDto comment) {
        if (this.postGrpcClient.checkPostExists(comment.getPost().getId())) {
            comment.setCreatedDate(new Date());
            repository.save(modelMapper.map(comment, Comment.class));
            return comment;
        }
        throw new IllegalArgumentException();
    }

    @Transactional
    @Override
    public boolean removeComment(CommentDto comment) {
        repository.deleteByPostIdAndUserId(comment.getPost().getId(), comment.getUserId());
        return true;
    }

}
