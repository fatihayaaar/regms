package com.fayardev.regms.profileservice.service.abstracts;

import com.fayardev.regms.profileservice.dto.BaseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface IProfileQueryHandler<T extends BaseDto> extends IHandler<T> {

    T get(String id);

    Slice<T> getAll(Pageable pageable);
}
