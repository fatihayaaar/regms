package com.fayardev.regms.userservice.dtos;

import com.fayardev.regms.userservice.validates.UserValidate;
import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class UserDto extends BaseDto {

    @NotEmpty
    @NotBlank
    private String uuid;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = UserValidate.USERNAME_PATTERN)
    private String uid;

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String surname;

    @NotEmpty
    @Email
    private String emailAddress;

    private String phoneNo;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = UserValidate.PASSWORD_PATTERN)
    private String password;

    @NotEmpty
    @NotBlank
    private String gender;

    @NotEmpty
    @NotBlank
    private String birthOfDate;

    private byte[] jpegPhoto;

    private boolean confirm;

    private boolean isActive;

    private boolean verified;
}
