package com.fayardev.regms.profileservice.service.grpc;

import com.fayardev.profileservice.profile.ProfileServiceGrpc;
import com.fayardev.regms.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcProfileService extends ProfileServiceGrpc.ProfileServiceImplBase {

    private final ProfileRepository repository;
    private final ModelMapper modelMapper;
}
