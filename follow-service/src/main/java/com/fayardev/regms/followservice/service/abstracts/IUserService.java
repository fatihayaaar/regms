package com.fayardev.regms.followservice.service.abstracts;

import com.fayardev.regms.followservice.dto.BaseDto;

public interface IUserService<T extends BaseDto> extends IService<T> {

    T createUser(T userDto);

    boolean followUser(String id, String followUsername);

    boolean unfollowUser(String id, String unfollowId);
}
