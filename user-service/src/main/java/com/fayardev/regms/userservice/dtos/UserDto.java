package com.fayardev.regms.userservice.dtos;

import com.fayardev.regms.userservice.entities.User;
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
    @Size(min = User.USERNAME_MIN_LENGTH, max = User.USERNAME_MAX_LENGTH)
    @Pattern(regexp = UserValidate.USERNAME_PATTERN)
    private String uid;

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String surname;

    @NotEmpty
    @Size(max = User.EMAIL_ADDRESS_MAX_LENGTH)
    @Email
    private String emailAddress;

    @Size(max = User.PHONE_NO_MAX_LENGTH)
    private String phoneNo;

    @NotEmpty
    @NotBlank
    @Size(min = User.PASSWORD_MIN_LENGTH, max = User.PASSWORD_MAX_LENGTH)
    @Pattern(regexp = UserValidate.PASSWORD_PATTERN)
    private String password;

    @NotEmpty
    @NotBlank
    private String gender;

    @NotEmpty
    @NotBlank
    private String birthOfDate;

    private byte[] jpegPhoto;
}
