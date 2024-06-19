package com.fayardev.regms.profileservice.service.abstracts;

import com.fayardev.regms.profileservice.dto.BaseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface IService<T extends BaseDto> {

    T add(T entity);

    void delete(String id);

    T update(T entity);

    T get(String id);

    Slice<T> getAll(Pageable pageable);
}
