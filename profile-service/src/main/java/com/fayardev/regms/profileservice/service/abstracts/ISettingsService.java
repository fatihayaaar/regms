package com.fayardev.regms.profileservice.service.abstracts;

import com.fayardev.regms.profileservice.dto.BaseDto;

import java.util.UUID;

public interface ISettingsService<T extends BaseDto> extends IService<T> {

    void changeNotificationsEnabled(UUID uuid, boolean notificationsEnabled);
}
