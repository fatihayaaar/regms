package com.fayardev.regms.commentservice.service.abstracts;

import com.fayardev.regms.commentservice.dto.BaseDto;

import java.util.List;

public interface ICommentQueryHandler<T extends BaseDto> extends IHandler<T> {

    long getCommentCountForPost(String postId);

    List<T> getCommentsByPostId(String postId);

    Iterable<T> getAll();
}
