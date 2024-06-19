package com.fayardev.regms.followservice.service.abstracts;

import com.fayardev.regms.followservice.dto.BaseDto;
import com.fayardev.regms.followservice.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface IService<T extends BaseDto> {

    Iterable<T> getAll();
}
