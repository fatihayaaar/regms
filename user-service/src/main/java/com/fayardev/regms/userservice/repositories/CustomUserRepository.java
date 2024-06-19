package com.fayardev.regms.userservice.repositories;

import com.fayardev.regms.userservice.entities.User;

public interface CustomUserRepository {

    boolean updateLdapUsername(User user);

    boolean updateLdapName(User user);

    boolean updateLdapSurname(User user);

    boolean updateLdapEmailAddress(User user);

    boolean updateLdapConfirm(User user);

    boolean updateLdapIsActive(User user);

    boolean updateLdapVerified(User user);

    boolean updateLdapGender(User user);

    boolean updateLdapBirthOfDate(User user);

    boolean updateLdapJpegPhoto(User user);
}
