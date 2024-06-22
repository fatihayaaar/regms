package com.fayardev.regms.userservice.services.abstracts;

import com.fayardev.regms.userservice.dtos.BaseDto;
import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;

public interface IUserCommandHandler<T extends BaseDto> extends IUserHandler {

    String saveEntity(T entity) throws Exception;

    boolean delete(String uuid) throws Exception;

    boolean update(User entity) throws Exception;

    boolean changeUsername(T user) throws UserException;

    boolean changeEmailAddress(T user) throws UserException;

    boolean changePhoneNo(T user) throws UserException;

    boolean changeName(T user) throws UserException;

    boolean changeSurname(T user) throws UserException;

    boolean changeConfirm(T user) throws UserException;

    boolean changeIsActive(T user) throws UserException;

    boolean changeVerified(T user) throws UserException;

    boolean changeGender(T user) throws UserException;

    boolean changeBirthOfDate(T user) throws UserException;

    boolean changeJpegPhoto(T user) throws UserException;

    boolean deleteJpegPhoto(UserDto user) throws UserException;

    boolean freeze(T user) throws UserException;
}
