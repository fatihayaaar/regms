package com.fayardev.regms.profileservice.service.abstracts;

import com.fayardev.regms.profileservice.dto.BaseDto;
import com.fayardev.regms.profileservice.dto.ProfileDto;

public interface IProfileCommandHandler<T extends BaseDto> extends IHandler<T> {

    T add(T entity);

    void delete(String id);

    T update(T entity);

    void changeBiography(T profile);

    void changeIsPrivate(T profile);

    void changeIsNotifications(T profileDto);

    void changeBackgroundImage(T profileDto);

    void deleteBackgroundImage(T profileDto);
}
