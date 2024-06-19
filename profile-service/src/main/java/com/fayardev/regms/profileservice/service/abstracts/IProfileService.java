package com.fayardev.regms.profileservice.service.abstracts;

import com.fayardev.regms.profileservice.dto.BaseDto;

public interface IProfileService<T extends BaseDto> extends IService<T> {

    void changeBiography(T profile);

    void changeIsPrivate(T profile);
}
