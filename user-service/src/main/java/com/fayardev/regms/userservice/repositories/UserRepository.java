package com.fayardev.regms.userservice.repositories;

import com.fayardev.regms.userservice.entities.User;
import org.springframework.data.ldap.repository.LdapRepository;

import java.util.Optional;

public interface UserRepository extends LdapRepository<User> {

    Optional<User> getUserByEmailAddress(String emailAddress);

    Optional<User> getUserByPhoneNo(String phoneNo);

    Optional<User> getUserByUid(String username);

    void deleteUserByUid(String username);
}
