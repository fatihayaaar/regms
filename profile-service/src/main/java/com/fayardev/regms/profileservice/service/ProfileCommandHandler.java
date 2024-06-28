package com.fayardev.regms.profileservice.service;

import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.entity.Profile;
import com.fayardev.regms.profileservice.repository.ProfileRepository;
import com.fayardev.regms.profileservice.service.abstracts.IProfileCommandHandler;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileCommandHandler implements IProfileCommandHandler<ProfileDto> {

    private final ProfileRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ProfileDto add(ProfileDto entity) {
        entity.setId(UUID.randomUUID().toString());
        repository.save(modelMapper.map(entity, Profile.class));
        return entity;
    }

    @Override
    public void changeBiography(ProfileDto profileDto) {
        Optional<Profile> profile = repository.getProfilesByUserId(profileDto.getUserId());
        Profile updatedProfile = profile.map(it -> {
            it.setBiography(profileDto.getBiography());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        repository.save(updatedProfile);
    }

    @Override
    public void changeIsPrivate(ProfileDto profileDto) {
        Optional<Profile> profile = repository.getProfilesByUserId(profileDto.getUserId());
        Profile updatedProfile = profile.map(it -> {
            it.setPrivate(profileDto.isPrivate());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        repository.save(updatedProfile);
    }

    @Override
    public void changeIsNotifications(ProfileDto profileDto) {
        Optional<Profile> profile = repository.getProfilesByUserId(profileDto.getUserId());
        Profile updatedProfile = profile.map(it -> {
            it.setNotificationsEnabled(profileDto.isNotificationsEnabled());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        repository.save(updatedProfile);
    }


    @Override
    public void changeBackgroundImage(ProfileDto profileDto) {
        Optional<Profile> profile = repository.getProfilesByUserId(profileDto.getUserId());
        Profile updatedProfile = profile.map(it -> {
            it.setBackgroundImage(profileDto.getBackgroundImage());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        repository.save(updatedProfile);
    }

    @Override
    public void deleteBackgroundImage(ProfileDto profileDto) {
        Optional<Profile> profile = repository.getProfilesByUserId(profileDto.getUserId());
        Profile updatedProfile = profile.map(it -> {
            it.setBackgroundImage("");
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        repository.save(updatedProfile);
    }

    @Override
    public void delete(String id) {
        Profile profile = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        repository.delete(profile);
    }

    @Override
    public ProfileDto update(ProfileDto entity) {
        Optional<Profile> profile = repository.findById(entity.getId());
        Profile updatedProfile = profile.map(it -> {
            it.setBiography(entity.getBiography());
            it.setPrivate(entity.isPrivate());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedProfile);
        return modelMapper.map(updatedProfile, ProfileDto.class);
    }
}
