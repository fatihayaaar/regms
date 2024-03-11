package com.fayardev.regms.userservice.dtos;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserDetailsDto extends BaseDto {

    private String username;
    private String firstname;
    private String surname;
}
