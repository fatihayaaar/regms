package com.fayardev.regms.profileservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class ProfileDTO extends BaseDTO {
    private UUID uuid;
    private UUID userId;
    private String biography;
    private SettingsDTO settings;
    private boolean isPrivate;
}
