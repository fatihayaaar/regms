package com.fayardev.regms.profileservice.service.abstracts;

import com.fayardev.regms.profileservice.dto.BaseDto;
import com.fayardev.regms.profileservice.dto.ProfileDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface IProfileQueryHandler<T extends BaseDto> extends IHandler<T> {

    T get(String id);

    T getByUsername(String username);

    Slice<T> getAll(Pageable pageable);
}
