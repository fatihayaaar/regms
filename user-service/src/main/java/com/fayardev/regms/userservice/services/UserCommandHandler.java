package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.dtos.ProfileDto;
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
    private final ProfileService profileService;
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
            String uuid = repository.save(entity).getUuid();

            ProfileDto profile = new ProfileDto();
            profile.setUserId(uuid);
            profileService.addProfile(profile);

            return uuid;
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
    public boolean update(User entity) {
        repository.save(entity);
        return true;
    }

    @Override
    public boolean changeUsername(UserDto user) throws UserException {
        var username = user.getUid().trim().toLowerCase();

        if (UserValidate.validateUsernameLength(username) && UserValidate.validateUsernamePattern(username)) {
            Optional<User> entity = repository.getUserByUuid(user.getUuid());
            if (entity.isPresent()) {
                User existingUser = entity.get();
                existingUser.setUid(username);

                return this.repository.updateLdapUsername(existingUser);
            }
        }
        return false;
    }

    @Override
    public boolean changeEmailAddress(UserDto user) throws UserException {
        var emailAddress = user.getEmailAddress().trim();

        if (UserValidate.validateEmailLength(emailAddress) && UserValidate.validateEmailPattern(emailAddress)) {
            Optional<User> entity = repository.getUserByUuid(user.getUuid());
            if (entity.isPresent()) {
                User existingUser = entity.get();
                existingUser.setEmailAddress(emailAddress);

                return this.repository.updateLdapEmailAddress(existingUser);
            }
        }
        return false;
    }

    @Override
    public boolean changePhoneNo(UserDto user) {
        return false;
    }

    @Override
    public boolean freeze(UserDto user) {
        Optional<User> entity = repository.getUserByUuid(user.getUuid());
        if (entity.isPresent()) {
            User existingUser = entity.get();
            existingUser.setActive(!existingUser.isActive());

            return this.repository.updateLdapIsActive(existingUser);
        }
        return false;
    }

}