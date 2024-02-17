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
@Table("settings")
public class Settings extends BaseEntity {
    @PrimaryKey
    private UUID uuid;
    private UUID profileId;
    private boolean notificationsEnabled;
}