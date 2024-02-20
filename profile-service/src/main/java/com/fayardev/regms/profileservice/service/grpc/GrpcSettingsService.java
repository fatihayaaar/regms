package com.fayardev.regms.profileservice.service.grpc;

import com.fayardev.regms.profileservice.repository.SettingsRepository;
import com.fayardev.settings.SettingsServiceGrpc;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GrpcSettingsService extends SettingsServiceGrpc.SettingsServiceImplBase {

    private final SettingsRepository repository;
    private final ModelMapper modelMapper;

    public GrpcSettingsService(SettingsRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
}
