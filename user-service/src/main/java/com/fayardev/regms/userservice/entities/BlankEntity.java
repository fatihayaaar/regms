package com.fayardev.regms.userservice.entities;

public final class BlankEntity extends BaseEntity {

    public BlankEntity() {
        super.ID = -1L;
    }

    @Override
    public String toString() {
        return "-1";
    }
}

