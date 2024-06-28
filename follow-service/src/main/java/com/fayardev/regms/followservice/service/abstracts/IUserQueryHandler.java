package com.fayardev.regms.followservice.service.abstracts;

import com.fayardev.regms.followservice.dto.BaseDto;

import java.util.List;

public interface IUserQueryHandler<T extends BaseDto> extends IHandler<T> {

    int getFolloweeCount(String username);

    int getFollowerCount(String username);

    int getFolloweeCountById(String userId);

    int getFollowerCountById(String userId);

    List<String> getFollowees(String username);

    List<String> getFollowers(String username);

    List<String> getFolloweesById(String userId);

    List<String> getFollowersById(String userId);

    Iterable<T> getAll();
}
