package com.fayardev.regms.profileservice.service;

import com.fayardev.regms.profileservice.dto.SettingsDTO;
import com.fayardev.regms.profileservice.entity.Settings;
import com.fayardev.regms.profileservice.repository.SettingsRepository;
import com.fayardev.regms.profileservice.service.abstracts.ISettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class SettingsService implements ISettingsService<SettingsDTO> {

    private final SettingsRepository repository;
    private final ModelMapper modelMapper;

    public SettingsService(SettingsRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SettingsDTO add(SettingsDTO entity) {
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
    public SettingsDTO update(SettingsDTO entity) {
        Optional<Settings> settings = repository.findById(entity.getUuid());
        Settings updatedSettings = settings.map(it -> {
            it.setNotificationsEnabled(entity.isNotificationsEnabled());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(updatedSettings);
        return modelMapper.map(updatedSettings, SettingsDTO.class);
    }

    @Override
    public SettingsDTO get(UUID uuid) {
        return modelMapper.map(repository.findById(uuid).orElseThrow(IllegalAccessError::new), SettingsDTO.class);
    }

    @Override
    public Slice<SettingsDTO> getAll(Pageable pageable) {
        return null;
    }
}
