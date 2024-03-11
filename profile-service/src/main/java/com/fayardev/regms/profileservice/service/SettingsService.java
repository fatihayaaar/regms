package com.fayardev.regms.profileservice.service;

import com.fayardev.regms.profileservice.dto.SettingsDto;
import com.fayardev.regms.profileservice.entity.Settings;
import com.fayardev.regms.profileservice.repository.SettingsRepository;
import com.fayardev.regms.profileservice.service.abstracts.ISettingsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SettingsService implements ISettingsService<SettingsDto> {

    private final SettingsRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public void changeNotificationsEnabled(UUID id, boolean notificationsEnabled) {
        Optional<Settings> settings = repository.findById(id);
        Settings updatedSettings = settings.map(it -> {
            it.setNotificationsEnabled(notificationsEnabled);
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        repository.save(updatedSettings);
    }

    @Override
    public SettingsDto add(SettingsDto entity) {
        Settings settings = repository.save(modelMapper.map(entity, Settings.class));
        entity.setId(settings.getId());
        return entity;
    }

    @Override
    public void delete(UUID id) {
        Settings settings = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        repository.delete(settings);
    }

    @Override
    public SettingsDto update(SettingsDto entity) {
        Optional<Settings> settings = repository.findById(entity.getId());
        Settings updatedSettings = settings.map(it -> {
            it.setNotificationsEnabled(entity.isNotificationsEnabled());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedSettings);
        return modelMapper.map(updatedSettings, SettingsDto.class);
    }

    @Override
    public SettingsDto get(UUID id) {
        return modelMapper.map(repository.findById(id).orElseThrow(IllegalAccessError::new), SettingsDto.class);
    }

    @Override
    public Slice<SettingsDto> getAll(Pageable pageable) {
        return null;
    }
}
