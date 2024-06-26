package com.fayardev.regms.followservice.service.abstracts;

import com.fayardev.regms.followservice.dto.BaseDto;

import java.util.List;

public interface IUserQueryHandler<T extends BaseDto> extends IHandler<T> {

    int getFolloweeCount(String username);

    int getFollowerCount(String username);

    int getFolloweeCountById(String userId);

    int getFollowerCountById(String userId);

    List<T> getFollowees(String username);

    List<T> getFollowers(String username);

    List<T> getFolloweesById(String userId);

    List<T> getFollowersById(String userId);

    Iterable<T> getAll();
}
