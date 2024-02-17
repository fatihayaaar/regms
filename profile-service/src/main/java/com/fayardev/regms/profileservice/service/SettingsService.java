package com.fayardev.regms.profileservice.service;

import com.fayardev.regms.profileservice.dto.SettingsDto;
import com.fayardev.regms.profileservice.entity.Settings;
import com.fayardev.regms.profileservice.repository.SettingsRepository;
import com.fayardev.regms.profileservice.service.abstracts.ISettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SettingsService implements ISettingsService<SettingsDto> {

    private final SettingsRepository repository;
    private final ModelMapper modelMapper;

    public SettingsService(SettingsRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SettingsDto add(SettingsDto entity) {
        Settings settings = repository.save(modelMapper.map(entity, Settings.class));
        entity.setUuid(settings.getUuid());
        return entity;
    }

    @Override
    public void delete(UUID uuid) {
        Settings settings = repository.findById(uuid).orElseThrow(IllegalArgumentException::new);
        repository.delete(settings);
    }

    @Override
    public SettingsDto update(SettingsDto entity) {
        Optional<Settings> settings = repository.findById(entity.getUuid());
        Settings updatedSettings = settings.map(it -> {
            it.setNotificationsEnabled(entity.isNotificationsEnabled());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedSettings);
        return modelMapper.map(updatedSettings, SettingsDto.class);
    }

    @Override
    public SettingsDto get(UUID uuid) {
        return modelMapper.map(repository.findById(uuid).orElseThrow(IllegalAccessError::new), SettingsDto.class);
    }

    @Override
    public Slice<SettingsDto> getAll(Pageable pageable) {
        return null;
    }
}
