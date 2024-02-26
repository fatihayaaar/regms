package com.fayardev.regms.profileservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class ProfileDto extends BaseDto {
    private UUID uuid;
    private UUID userUuid;
    private String biography;
    private UUID settingsUuid;
    private boolean isPrivate;
}
