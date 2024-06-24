package com.fayardev.regms.userservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entry(objectClasses = {"inetOrgPerson", "top"}, base = "ou=users")
public final class User extends BaseEntity {

    @Id
    private Name dn;

    @Attribute(name = "uuid")
    private String uuid;

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

    @Attribute(name = "avatar")
    private String avatar;
}
