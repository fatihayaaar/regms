package com.fayardev.regms.likeservice.service.abstracts;

import com.fayardev.regms.likeservice.dto.BaseDto;

public interface IUserCommandHandler<T extends BaseDto> extends IHandler<T> {

    T createUser(T userDto);

    boolean followUser(String username, String userId);

    boolean unfollowUser(String username, String userId);

    boolean isFollowing(String userId, String username);
}
