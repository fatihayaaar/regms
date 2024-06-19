package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.client.FollowerClient;
import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.dtos.UserFollowerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerService {

    private final FollowerClient client;

    public void createUser(UserFollowerDto userFollowerDto) {
        client.createUser(userFollowerDto);
    }
}
