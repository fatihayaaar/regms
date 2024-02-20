package com.fayardev.regms.profileservice.service.grpc;

import com.fayardev.profileservice.profile.ProfileServiceGrpc;
import com.fayardev.regms.profileservice.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GrpcProfileService extends ProfileServiceGrpc.ProfileServiceImplBase {

    private final ProfileRepository repository;
    private final ModelMapper modelMapper;

    public GrpcProfileService(ProfileRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
}
