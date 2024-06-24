package com.fayardev.regms.profileservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table("profile")
public class Profile extends BaseEntity {

    @PrimaryKey
    private String id;
    private String userId;
    private String biography;
    private boolean isPrivate;
    private boolean notificationsEnabled;
    private String backgroundImage;
}
