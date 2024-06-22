package com.fayardev.regms.profileservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class ProfileDto extends BaseDto {

    private String id;
    private String userId;
    private String biography;
    private boolean isPrivate;
    private boolean notificationsEnabled;
    private byte[] backgroundImage;
    private String username;
    private String jpegPhoto;
    private String name;
    private String surname;
}
