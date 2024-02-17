package com.fayardev.regms.postservice.service.abstracts;

import com.fayardev.regms.postservice.dto.BaseDto;

import java.util.UUID;

public interface IPostService<T extends BaseDto> extends IService<T> {

    T visibility(UUID uuid, boolean visible);
}
