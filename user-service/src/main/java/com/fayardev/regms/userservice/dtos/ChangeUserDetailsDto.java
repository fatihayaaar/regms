package com.fayardev.regms.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeUserDetailsDto {

    private String username;

    private String firstname;

    private String surname;
}
