package com.fayardev.regms.userservice.services.abstracts;

import com.fayardev.regms.userservice.entities.BaseEntity;
import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;

public interface IUserService<T extends BaseEntity> extends IService<T> {

    BaseEntity getEntityByEmail(String email);

    BaseEntity getEntityByUsername(String username);

    BaseEntity getEntityByPhoneNo(String phoneNo);

    boolean changeUsername(User user) throws UserException;

    boolean changeEmailAddress(User user) throws UserException;

    boolean changePhoneNo(User user) throws UserException;

    boolean freeze(User user) throws UserException;
}
