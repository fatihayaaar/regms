package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.entities.BaseEntity;
import com.fayardev.regms.userservice.services.abstracts.IService;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T extends BaseEntity> implements IService<T> {

    protected BaseService() {
    }
}