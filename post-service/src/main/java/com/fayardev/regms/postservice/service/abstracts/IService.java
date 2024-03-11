package com.fayardev.regms.postservice.service.abstracts;

import com.fayardev.regms.postservice.dto.BaseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface IService<T extends BaseDto> {

    T add(T entity);

    void delete(UUID id);

    T update(T entity);

    T get(UUID id);

    Slice<T> getAll(Pageable pageable);
}
