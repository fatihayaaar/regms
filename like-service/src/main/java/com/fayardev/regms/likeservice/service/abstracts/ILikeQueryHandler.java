package com.fayardev.regms.likeservice.service.abstracts;

import com.fayardev.regms.likeservice.dto.BaseDto;

import java.util.List;

public interface ILikeQueryHandler<T extends BaseDto> extends IHandler<T> {

    long getLikesCountForPost(String postId);

    List<String> getUsersWhoLikedPost(String postId);

    boolean isPostLikedByUser(String postId, String userId);

    Iterable<T> getAll();
}
