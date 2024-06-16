package com.fayardev.regms.userservice.services.abstracts;

import com.fayardev.regms.userservice.dtos.BaseDto;

import java.util.List;

public interface IUserQueryHandler<T extends BaseDto> extends IUserHandler {

    T getEntityByEmail(String email);

    T getEntityByUsername(String username);

    T getEntityByPhoneNo(String phoneNo);

    List<T> getEntities();
}
