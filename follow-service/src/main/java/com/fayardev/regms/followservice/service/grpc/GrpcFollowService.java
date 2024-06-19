package com.fayardev.regms.followservice.service.grpc;

import com.fayardev.followservice.user.FollowServiceGrpc;
import com.fayardev.regms.followservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcFollowService extends FollowServiceGrpc.FollowServiceImplBase {

    private final UserRepository repository;
    private final ModelMapper modelMapper;
}
