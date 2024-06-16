package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;
import com.fayardev.regms.userservice.repositories.UserRepository;
import com.fayardev.regms.userservice.services.abstracts.IUserCommandHandler;
import com.fayardev.regms.userservice.validates.UserValidate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCommandHandler implements IUserCommandHandler<UserDto> {

    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public String saveEntity(UserDto userDto) {
        User entity = modelMapper.map(userDto, User.class);

        entity.setUuid(UUID.randomUUID().toString());
        entity.setUid(entity.getUid().trim().toLowerCase());
        entity.setActive(true);
        entity.setConfirm(false);
        entity.setVerified(false);

        if (UserValidate.validatePasswordLength(userDto.getPassword())
                && UserValidate.validatePassword(userDto.getPassword())
                && UserValidate.validateUser(entity)
                && isEmailUnique(entity.getEmailAddress())
                && isUsernameUnique(entity.getUid())) {
            return repository.save(entity).getUuid();
        }
        return null;
    }

    private boolean isEmailUnique(String emailAddress) throws UserException {
        Optional<User> existingUser = repository.getUserByEmailAddress(emailAddress);
        return existingUser
                .map(existing -> UserValidate.validateUniqueEmail(emailAddress, existing.getEmailAddress()))
                .orElse(true);
    }

    private boolean isUsernameUnique(String username) throws UserException {
        Optional<User> existingUser = repository.getUserByUid(username);
        return existingUser
                .map(existing -> UserValidate.validateUniqueUsername(username, existing.getUid()))
                .orElse(true);
    }

    @Override
    public boolean delete(String username) {
        repository.deleteUserByUid(username);
        return true;
    }

    @Override
    public boolean update(UserDto entity) {
        repository.save(modelMapper.map(entity, User.class));
        return true;
    }

    @Override
    public boolean changeUsername(UserDto user) throws UserException {
        var username = user.getUid().trim().toLowerCase();
        user.setUid(username);

        if (UserValidate.validateUsernameLength(username) && UserValidate.validateUsernamePattern(username)) {
            UserDto entity = repository.getUserByUid(username)
                    .map(user1 -> modelMapper.map(user1, UserDto.class))
                    .orElse(null);

            if (entity != null) {
                return this.update(user);
            }
        }
        return false;
    }

    @Override
    public boolean changeEmailAddress(UserDto user) throws UserException {
        var emailAddress = user.getEmailAddress().trim();

        if (UserValidate.validateEmailLength(emailAddress) && UserValidate.validateEmailPattern(emailAddress)) {
            UserDto entity = repository.getUserByEmailAddress(emailAddress)
                    .map(user1 -> modelMapper.map(user1, UserDto.class))
                    .orElse(null);

            if (entity != null) {
                return this.update(user);
            }
        }
        return false;
    }

    @Override
    public boolean changePhoneNo(UserDto user) {
        var phoneNo = user.getPhoneNo().trim();

        UserDto entity = repository.getUserByPhoneNo(phoneNo)
                .map(user1 -> modelMapper.map(user1, UserDto.class))
                .orElse(null);

        if (entity != null) {
            return this.update(user);
        }
        return false;
    }

    @Override
    public boolean freeze(UserDto user) {
        User entity = modelMapper.map(user, User.class);
        entity.setActive(!entity.isActive());
        return this.update(user);
    }

}