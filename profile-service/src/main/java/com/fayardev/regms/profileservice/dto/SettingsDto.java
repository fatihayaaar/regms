package com.fayardev.regms.profileservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class SettingsDto extends BaseDto {
    private UUID uuid;
    private UUID profileId;
    private boolean notificationsEnabled;
}
