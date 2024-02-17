package com.fayardev.regms.profileservice.service;

import com.fayardev.regms.profileservice.dto.ProfileDto;
import com.fayardev.regms.profileservice.entity.Profile;
import com.fayardev.regms.profileservice.entity.Settings;
import com.fayardev.regms.profileservice.repository.ProfileRepository;
import com.fayardev.regms.profileservice.service.abstracts.IProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService implements IProfileService<ProfileDto> {

    private final ProfileRepository repository;
    private final ModelMapper modelMapper;

    public ProfileService(ProfileRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProfileDto add(ProfileDto entity) {
        Profile profile = repository.save(modelMapper.map(entity, Profile.class));
        entity.setUuid(profile.getUuid());
        return entity;
    }

    @Override
    public void delete(UUID uuid) {
        Profile profile = repository.findById(uuid).orElseThrow(IllegalArgumentException::new);
        repository.delete(profile);
    }

    @Override
    public ProfileDto update(ProfileDto entity) {
        Optional<Profile> profile = repository.findById(entity.getUuid());
        Profile updatedProfile = profile.map(it -> {
            it.setBiography(entity.getBiography());
            it.setSettings(modelMapper.map(entity.getSettings(), Settings.class));
            it.setPrivate(entity.isPrivate());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedProfile);
        return modelMapper.map(updatedProfile, ProfileDto.class);
    }

    @Override
    public ProfileDto get(UUID uuid) {
        return modelMapper.map(repository.findById(uuid).orElseThrow(IllegalAccessError::new), ProfileDto.class);
    }

    @Override
    public Slice<ProfileDto> getAll(Pageable pageable) {
        return null;
    }
}
