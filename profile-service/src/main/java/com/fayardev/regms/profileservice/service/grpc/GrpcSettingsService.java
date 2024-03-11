package com.fayardev.regms.profileservice.service.grpc;

import com.fayardev.profileservice.settings.SettingsServiceGrpc;
import com.fayardev.regms.profileservice.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcSettingsService extends SettingsServiceGrpc.SettingsServiceImplBase {

    private final SettingsRepository repository;
    private final ModelMapper modelMapper;
}
