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

    private UUID id;
    private UUID userId;
    private String biography;
    private UUID settingsId;
    private boolean isPrivate;
}
