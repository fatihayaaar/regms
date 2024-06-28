package com.fayardev.regms.profileservice.service;

import com.fayardev.regms.profileservice.client.FollowClient;
import com.fayardev.regms.profileservice.client.UserGrpcClient;
import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.repository.ProfileRepository;
import com.fayardev.regms.profileservice.service.abstracts.IProfileQueryHandler;
import com.fayardev.userservice.user.UserResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileQueryHandler implements IProfileQueryHandler<ProfileDto> {

    private final ProfileRepository repository;
    private final UserGrpcClient userClient;
    private final FollowClient followClient;
    private final ModelMapper modelMapper;

    @Override
    public ProfileDto get(String id) {
        UserResponse user = userClient.getUserByUuid(id);

        ProfileDto profile = modelMapper.map(repository.getProfilesByUserId(id).orElseThrow(IllegalAccessError::new), ProfileDto.class);
        return getProfileDto(user, profile);
    }

    @Override
    public ProfileDto getByUsername(String username) {
        UserResponse user = userClient.getUserByUsername(username);

        ProfileDto profile = modelMapper.map(repository.getProfilesByUserId(user.getUuid()).orElseThrow(IllegalAccessError::new), ProfileDto.class);
        return getProfileDto(user, profile);
    }

    @NotNull
    private ProfileDto getProfileDto(UserResponse user, ProfileDto profile) {
        profile.setUsername(user.getUid());
        profile.setName(user.getName());
        profile.setSurname(user.getSurname());
        profile.setAvatar(user.getAvatar());
        profile.setFollowing(followClient.isFollowing(user.getUid()));
        profile.setFolloweeCount(followClient.getFolloweeCount(user.getUid()));
        profile.setFollowerCount(followClient.getFollowerCount(user.getUid()));

        return profile;
    }

    @Override
    public Slice<ProfileDto> getAll(Pageable pageable) {
        return null;
    }
}
