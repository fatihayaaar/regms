package com.fayardev.regms.userservice.repositories;

import com.fayardev.regms.userservice.entities.User;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import java.util.Optional;

public interface UserRepository extends LdapRepository<User>, CustomUserRepository {

    Optional<User> getUserByEmailAddress(String emailAddress);

    Optional<User> getUserByUuid(String uuid);

    Optional<User> getUserByPhoneNo(String phoneNo);

    Optional<User> getUserByUid(String username);

    void deleteUserByUid(String username);

    void deleteUserByUuid(String uuid);
}
