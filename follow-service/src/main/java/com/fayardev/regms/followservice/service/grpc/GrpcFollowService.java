package com.fayardev.regms.followservice.service.grpc;

import com.fayardev.followservice.follow.FollowServiceGrpc;
import com.fayardev.regms.followservice.repository.FollowRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GrpcFollowService extends FollowServiceGrpc.FollowServiceImplBase {

    private final FollowRepository repository;
    private final ModelMapper modelMapper;

    public GrpcFollowService(FollowRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
}
