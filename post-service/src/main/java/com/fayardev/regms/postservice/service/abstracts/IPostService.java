package com.fayardev.regms.postservice.service.abstracts;

import com.fayardev.regms.postservice.dto.BaseDTO;

import java.util.UUID;

public interface IPostService<T extends BaseDTO> extends IService<T> {

    T visibility(UUID uuid, boolean visible);
}
