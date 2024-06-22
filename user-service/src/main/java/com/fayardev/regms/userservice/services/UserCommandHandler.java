package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.dtos.ProfileDto;
import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.dtos.UserFollowerDto;
import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;
import com.fayardev.regms.userservice.repositories.UserRepository;
import com.fayardev.regms.userservice.services.abstracts.IUserCommandHandler;
import com.fayardev.regms.userservice.validates.UserValidate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserCommandHandler implements IUserCommandHandler<UserDto> {

    private final UserRepository repository;
    private final ProfileService profileService;
    private final FollowerService followerService;
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

            UserFollowerDto userFollowerDto = new UserFollowerDto();
            userFollowerDto.setId(uuid);
            followerService.createUser(userFollowerDto);

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
    public boolean changeName(UserDto user) throws UserException {
        var name = user.getName().trim();
        UserValidate.validateNameLength(name);
        return updateUserField(user.getUuid(), u -> u.setName(name), repository::updateLdapName);
    }

    @Override
    public boolean changeSurname(UserDto user) throws UserException {
        var surname = user.getSurname().trim();

        if (UserValidate.validateSurnameLength(surname)) {
            Optional<User> entity = repository.getUserByUuid(user.getUuid());
            if (entity.isPresent()) {
                User existingUser = entity.get();
                existingUser.setSurname(surname);

                return this.repository.updateLdapSurname(existingUser);
            }
        }
        return false;
    }

    @Override
    public boolean changeConfirm(UserDto user) throws UserException {
        boolean confirm = user.isConfirm();

        Optional<User> entity = repository.getUserByUuid(user.getUuid());
        if (entity.isPresent()) {
            User existingUser = entity.get();
            existingUser.setConfirm(confirm);

            return this.repository.updateLdapConfirm(existingUser);
        }
        return false;
    }

    @Override
    public boolean changeIsActive(UserDto user) throws UserException {
        boolean isActive = user.isActive();

        Optional<User> entity = repository.getUserByUuid(user.getUuid());
        if (entity.isPresent()) {
            User existingUser = entity.get();
            existingUser.setActive(isActive);

            return this.repository.updateLdapIsActive(existingUser);
        }
        return false;
    }

    @Override
    public boolean changeVerified(UserDto user) throws UserException {
        boolean verified = user.isVerified();

        Optional<User> entity = repository.getUserByUuid(user.getUuid());
        if (entity.isPresent()) {
            User existingUser = entity.get();
            existingUser.setVerified(verified);

            return this.repository.updateLdapVerified(existingUser);
        }
        return false;
    }

    @Override
    public boolean changeGender(UserDto user) throws UserException {
        var gender = user.getGender().trim();

        Optional<User> entity = repository.getUserByUuid(user.getUuid());
        if (entity.isPresent()) {
            User existingUser = entity.get();
            existingUser.setGender(gender);

            return this.repository.updateLdapGender(existingUser);
        }
        return false;
    }

    @Override
    public boolean changeBirthOfDate(UserDto user) throws UserException {
        var birthOfDate = user.getBirthOfDate().trim();

        Optional<User> entity = repository.getUserByUuid(user.getUuid());
        if (entity.isPresent()) {
            User existingUser = entity.get();
            existingUser.setBirthOfDate(birthOfDate);

            return this.repository.updateLdapBirthOfDate(existingUser);
        }
        return false;
    }

    @Override
    public boolean changeJpegPhoto(UserDto user) throws UserException {
        var jpegPhoto = user.getJpegPhoto();

        Optional<User> entity = repository.getUserByUuid(user.getUuid());
        if (entity.isPresent()) {
            User existingUser = entity.get();
            existingUser.setJpegPhoto(jpegPhoto);

            return this.repository.updateLdapJpegPhoto(existingUser);
        }
        return false;
    }

    @Override
    public boolean deleteJpegPhoto(UserDto user) throws UserException {
        Optional<User> entity = repository.getUserByUuid(user.getUuid());
        if (entity.isPresent()) {
            User existingUser = entity.get();
            existingUser.setJpegPhoto("".getBytes(StandardCharsets.UTF_8));

            return this.repository.updateLdapJpegPhoto(existingUser);
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

    private boolean updateUserField(String uuid, Consumer<User> updater, Function<User, Boolean> repositoryFunction) throws UserException {
        Optional<User> entity = repository.getUserByUuid(uuid);
        if (entity.isPresent()) {
            User existingUser = entity.get();
            updater.accept(existingUser);
            return repositoryFunction.apply(existingUser);
        }
        return false;
    }
}