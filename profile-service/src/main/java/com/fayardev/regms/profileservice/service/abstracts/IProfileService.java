package com.fayardev.regms.profileservice.service.abstracts;

import com.fayardev.regms.profileservice.dto.BaseDto;

import java.util.UUID;

public interface IProfileService<T extends BaseDto> extends IService<T> {

    void changeBiography(UUID id, String biography);

    void changeIsPrivate(UUID id, boolean isPrivate);
}
