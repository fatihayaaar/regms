package com.fayardev.regms.userservice.repositories;

import com.fayardev.regms.userservice.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.ldap.repository.LdapRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends LdapRepository<User>, CustomUserRepository {

    Optional<User> getUserByEmailAddress(String emailAddress);

    Optional<User> getUserByUuid(String uuid);

    Optional<User> getUserByPhoneNo(String phoneNo);

    Optional<User> getUserByUid(String username);

    void deleteUserByUid(String username);

    void deleteUserByUuid(String uuid);

}
