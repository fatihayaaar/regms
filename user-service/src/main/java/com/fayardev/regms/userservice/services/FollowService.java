package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.client.FollowClient;
import com.fayardev.regms.userservice.dtos.UserFollowerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowClient client;

    public void createUser(UserFollowerDto userFollowerDto) {
        client.createUser(userFollowerDto);
    }
}
