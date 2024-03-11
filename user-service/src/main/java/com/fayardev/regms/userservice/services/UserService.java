package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.dtos.ChangeUserDetailsDto;
import com.fayardev.regms.userservice.entities.BaseEntity;
import com.fayardev.regms.userservice.entities.BlankEntity;
import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;
import com.fayardev.regms.userservice.exceptions.enums.ErrorComponents;
import com.fayardev.regms.userservice.repositories.UserRepository;
import com.fayardev.regms.userservice.services.abstracts.IUserService;
import com.fayardev.regms.userservice.validates.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User> implements IUserService<User> {

    private final UserRepository repository;

    @Override
    public boolean saveEntity(User entity) throws Exception {
        entity.setUid(entity.getUid().trim().toLowerCase());
        if (!UserValidate.userValidate(entity)) {
            return false;
        }
        if (!emailControl(entity) || !usernameControl(entity)) {
            return false;
        }
        entity.setActive(true);
        entity.setConfirm(false);
        entity.setVerified(false);
        repository.save(entity);
        return true;
    }

    private boolean emailControl(User user) throws UserException {
        User userLocal = repository.getUserByEmailAddress(user.getEmailAddress()).orElse(null);
        if (userLocal != null) {
            return UserValidate.emailEquals(user.getEmailAddress(), userLocal.getEmailAddress(), ErrorComponents.EMAIL);
        }
        return true;
    }

    private boolean usernameControl(User user) throws UserException {
        User userLocal = repository.getUserByUid(user.getUid()).orElse(null);
        if (userLocal != null) {
            return UserValidate.usernameEquals(user.getUid(), userLocal.getUid(), ErrorComponents.USERNAME);
        }
        return true;
    }

    @Override
    public boolean delete(String username) throws Exception {
        repository.deleteUserByUid(username);
        return true;
    }

    @Override
    public boolean update(User entity) throws UserException {
        repository.save(entity);
        return true;
    }

    @Override
    public List<User> getEntities() {
        return repository.findAll();
    }

    @Override
    public BaseEntity getEntityByEmail(String email) {
        return repository.getUserByEmailAddress(email).orElse(null);
    }

    @Override
    public BaseEntity getEntityByUsername(String username) {
        return repository.getUserByUid(username).orElse(null);
    }

    @Override
    public BaseEntity getEntityByPhoneNo(String phoneNo) {
        return repository.getUserByPhoneNo(phoneNo).orElse(null);
    }

    public boolean changeUserDetails(ChangeUserDetailsDto changeUserDetailsDto) {

        return true;
    }

    @Override
    public boolean changeUsername(User user) throws UserException {
        var username = user.getUid().trim().toLowerCase();
        user.setUid(username);
        if (!UserValidate.strUsernameLengthValidate(username)) {
            return false;
        }
        if (!UserValidate.usernameRegexValidate(username)) {
            return false;
        }
        BaseEntity entity = this.getEntityByUsername(username);
        if (entity instanceof BlankEntity) {
            return this.update(user);
        }
        return false;
    }

    @Override
    public boolean changeEmailAddress(User user) throws UserException {
        var emailAddress = user.getEmailAddress().trim();
        if (!UserValidate.emailLengthValidate(emailAddress)) {
            return false;
        }
        if (!UserValidate.emailRegexValidate(emailAddress)) {
            return false;
        }
        BaseEntity entity = this.getEntityByEmail(emailAddress);
        if (entity instanceof BlankEntity) {
            return this.update(user);
        }
        return false;
    }

    @Override
    public boolean changePhoneNo(User user) throws UserException {
        var phoneNo = user.getPhoneNo().trim();

        BaseEntity entity = this.getEntityByPhoneNo(phoneNo);
        if (entity instanceof BlankEntity) {
            return this.update(user);
        }
        return false;
    }

    @Override
    public boolean freeze(User user) throws UserException {
        user.setActive(!user.isActive());
        return this.update(user);
    }

}