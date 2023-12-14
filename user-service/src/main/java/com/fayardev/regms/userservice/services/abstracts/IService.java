package com.fayardev.regms.userservice.services.abstracts;

import com.fayardev.regms.userservice.entities.BaseEntity;

import java.util.List;

public interface IService<T extends BaseEntity> {

    boolean saveEntity(T entity) throws Exception;

    boolean delete(String username) throws Exception;

    boolean update(T entity) throws Exception;

    List<T> getEntities();
}

