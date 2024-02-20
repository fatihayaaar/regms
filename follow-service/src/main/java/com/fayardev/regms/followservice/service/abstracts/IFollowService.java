package com.fayardev.regms.followservice.service.abstracts;

import com.fayardev.regms.followservice.dto.BaseDto;

import java.util.UUID;

public interface IFollowService<T extends BaseDto> extends IService<T> {

    void follow(UUID followerID, UUID followingID);

    void unFollow(UUID followerID, UUID followingID);
}
