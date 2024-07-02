package com.fayardev.regms.commentservice.service.abstracts;

import com.fayardev.regms.commentservice.dto.BaseDto;

public interface IUserCommandHandler<T extends BaseDto> extends IHandler<T> {

    T createUser(T userDto);

    boolean followUser(String username, String userId);

    boolean unfollowUser(String username, String userId);

    boolean isFollowing(String userId, String username);
}
