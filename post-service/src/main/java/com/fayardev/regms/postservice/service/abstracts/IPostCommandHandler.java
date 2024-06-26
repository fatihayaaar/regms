package com.fayardev.regms.postservice.service.abstracts;

import com.fayardev.regms.postservice.dto.BaseDto;

public interface IPostCommandHandler<T extends BaseDto> extends IHandler<T> {

    T add(T entity);

    boolean delete(T entity);

    T update(T entity);

    T visibility(String id, boolean visible);
}
