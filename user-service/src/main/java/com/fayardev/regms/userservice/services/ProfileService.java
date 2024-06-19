package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.client.ProfileClient;
import com.fayardev.regms.userservice.dtos.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileClient client;

    public void addProfile(ProfileDto profileDto) {
        client.add(profileDto);
    }
}
