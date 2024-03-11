package com.fayardev.regms.profileservice.service;

import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.entity.Profile;
import com.fayardev.regms.profileservice.repository.ProfileRepository;
import com.fayardev.regms.profileservice.service.abstracts.IProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService<ProfileDto> {

    private final ProfileRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public void changeBiography(UUID id, String biography) {
        Optional<Profile> profile = repository.findById(id);
        Profile updatedProfile = profile.map(it -> {
            it.setBiography(biography);
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        repository.save(updatedProfile);
    }

    @Override
    public void changeIsPrivate(UUID id, boolean isPrivate) {
        Optional<Profile> profile = repository.findById(id);
        Profile updatedProfile = profile.map(it -> {
            it.setPrivate(isPrivate);
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        repository.save(updatedProfile);
    }

    @Override
    public ProfileDto add(ProfileDto entity) {
        Profile profile = repository.save(modelMapper.map(entity, Profile.class));
        entity.setId(profile.getId());
        return entity;
    }

    @Override
    public void delete(UUID id) {
        Profile profile = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        repository.delete(profile);
    }

    @Override
    public ProfileDto update(ProfileDto entity) {
        Optional<Profile> profile = repository.findById(entity.getId());
        Profile updatedProfile = profile.map(it -> {
            it.setBiography(entity.getBiography());
            it.setSettingsId(entity.getSettingsId());
            it.setPrivate(entity.isPrivate());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedProfile);
        return modelMapper.map(updatedProfile, ProfileDto.class);
    }

    @Override
    public ProfileDto get(UUID id) {
        return modelMapper.map(repository.findById(id).orElseThrow(IllegalAccessError::new), ProfileDto.class);
    }

    @Override
    public Slice<ProfileDto> getAll(Pageable pageable) {
        return null;
    }
}
