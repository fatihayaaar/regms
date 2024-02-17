package com.fayardev.regms.postservice.service.abstracts;

import com.fayardev.regms.postservice.dto.BaseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface IService<T extends BaseDTO> {

    T add(T entity);

    void delete(UUID uuid);

    T update(T entity);

    T get(UUID uuid);

    Slice<T> getAll(Pageable pageable);
}
