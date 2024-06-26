package com.fayardev.regms.userservice.repositories;

import com.fayardev.regms.userservice.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapName;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryImpl implements CustomUserRepository {

    private final LdapTemplate ldapTemplate;

    @Override
    public boolean updateLdapUsername(User user) {
        try {
            Name currentDn = user.getDn();
            LdapName newDn = LdapNameBuilder.newInstance().add("ou", "users").add("uid", user.getUid()).build();
            ldapTemplate.rename(currentDn, newDn);
            user.setDn(newDn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateLdapName(User user) {
        return updateAttribute(user.getDn(), "cn", user.getName());
    }

    @Override
    public boolean updateLdapSurname(User user) {
        return updateAttribute(user.getDn(), "sn", user.getSurname());
    }

    @Override
    public boolean updateLdapEmailAddress(User user) {
        return updateAttribute(user.getDn(), "mail", user.getEmailAddress());
    }

    @Override
    public boolean updateLdapConfirm(User user) {
        return updateAttribute(user.getDn(), "confirm", String.valueOf(user.isConfirm()));
    }

    @Override
    public boolean updateLdapIsActive(User user) {
        return updateAttribute(user.getDn(), "isActive", String.valueOf(user.isActive()));
    }

    @Override
    public boolean updateLdapVerified(User user) {
        return updateAttribute(user.getDn(), "verified", String.valueOf(user.isVerified()));
    }

    @Override
    public boolean updateLdapGender(User user) {
        return updateAttribute(user.getDn(), "gender", user.getGender());
    }

    @Override
    public boolean updateLdapBirthOfDate(User user) {
        return updateAttribute(user.getDn(), "birthOfDate", user.getBirthOfDate());
    }

    @Override
    public boolean updateLdapAvatar(User user) {
        try {
            ldapTemplate.modifyAttributes(user.getDn(), new ModificationItem[]{new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("avatar", user.getAvatar()))});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean updateAttribute(Name dn, String attributeName, String attributeValue) {
        try {
            ldapTemplate.modifyAttributes(dn, new ModificationItem[]{new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(attributeName, attributeValue))});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> searchUsers(String query) {
        String[] parts = query.trim().split("\\s+");
        String surname = parts[parts.length - 1];

        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            if (!nameBuilder.isEmpty()) {
                nameBuilder.append(" ");
            }
            nameBuilder.append(parts[i]);
        }
        String name = nameBuilder.toString();

        String wildcardQueryName = "*" + name + "*";
        String wildcardQuerySurname = "*" + surname + "*";
        String wildcardQueryBoth = "*" + query + "*";

        LdapQuery ldapQuery = LdapQueryBuilder.query()
                .where("uid").like(wildcardQueryBoth)
                .or(LdapQueryBuilder.query().where("cn").like(wildcardQueryName).and("sn").like(wildcardQuerySurname))
                .or(LdapQueryBuilder.query().where("cn").like(wildcardQueryBoth))
                .or(LdapQueryBuilder.query().where("sn").like(wildcardQueryBoth));

        List<User> users = ldapTemplate.find(ldapQuery, User.class);

        if (users.isEmpty()) {
            return users;
        }

        List<User> sanitizedUsers = new ArrayList<>();
        users.forEach(user -> {
            user.setEmailAddress(null);
            user.setPassword(null);
            user.setPhoneNo(null);
            sanitizedUsers.add(user);
        });

        return sanitizedUsers.stream()
                .sorted((u1, u2) -> {
                    int score1 = getSimilarityScore(u1.getUid(), query);
                    int score2 = getSimilarityScore(u2.getUid(), query);
                    if (score1 == score2) {
                        score1 = getSimilarityScore(u1.getName() + " " + u1.getSurname(), query);
                        score2 = getSimilarityScore(u2.getName() + " " + u2.getSurname(), query);
                    }
                    return Integer.compare(score2, score1);
                })
                .toList();
    }

    private int getSimilarityScore(String value, String query) {
        return value.toLowerCase().split(query.toLowerCase(), -1).length - 1;
    }
}