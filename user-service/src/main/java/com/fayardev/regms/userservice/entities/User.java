package com.fayardev.regms.userservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entry(objectClasses = {"inetOrgPerson", "top"}, base = "ou=users")
public class User extends BaseEntity {

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 32;
    public static final int USERNAME_MAX_LENGTH = 11;
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int EMAIL_ADDRESS_MAX_LENGTH = 32;
    public static final int PHONE_NO_MAX_LENGTH = 32;
    public static final int SEX_MAX_LENGTH = 8;

    @Id
    private Name dn;

    @Attribute(name = "uid")
    @DnAttribute(value = "uid", index = 1)
    private String uid;

    @Attribute(name = "cn")
    private String name;

    @Attribute(name = "sn")
    private String surname;

    @Attribute(name = "mail")
    private String emailAddress;

    @Attribute(name = "confirm")
    private boolean confirm;

    @Attribute(name = "isActive")
    private boolean isActive;

    @Attribute(name = "verified")
    private boolean verified;

    @Transient
    private String phoneNo;

    @Attribute(name = "userPassword")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Attribute(name = "gender")
    private String gender;

    @Attribute(name = "birthOfDate")
    private String birthOfDate;

    @Attribute(name = "jpegPhoto")
    private byte[] jpegPhoto;

    @Override
    public String toString() {
        return null;
    }
}
