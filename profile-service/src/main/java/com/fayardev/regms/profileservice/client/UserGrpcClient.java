package com.fayardev.regms.profileservice.client;

import com.fayardev.userservice.user.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGrpcClient {

    //@GrpcClient("userService")
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;

}
