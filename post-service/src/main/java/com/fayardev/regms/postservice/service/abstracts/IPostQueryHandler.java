package com.fayardev.regms.postservice.service.abstracts;

import com.fayardev.regms.postservice.dto.BaseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostQueryHandler<T extends BaseDto> extends IHandler<T> {

    T get(String id);

    List<T>  getAll();
}
