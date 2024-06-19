package com.fayardev.regms.userservice.dtos;

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
}
