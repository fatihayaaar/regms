package com.fayardev.regms.userservice.entities;

import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class BaseEntity implements Serializable {

    protected Long id;

    protected BaseEntity() {
        super();
    }

    public abstract String toString();
}
