package com.fayardev.regms.profileservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table("profile")
public class Profile extends BaseEntity {
    @PrimaryKey
    private UUID uuid;
    private UUID userId;
    private String biography;
    private Settings settings;
    private boolean isPrivate;
}
