package com.fayardev.regms.followservice.service.abstracts;

import com.fayardev.regms.followservice.dto.BaseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface IService<T extends BaseDto> {

    T add(T entity);

    void delete(UUID uuid);

    T update(T entity);

    T get(UUID uuid);

    Slice<T> getAll(Pageable pageable);
}
