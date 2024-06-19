package com.fayardev.regms.userservice.validates;

import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;

import java.util.regex.Pattern;

public class UserValidate {

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 32;
    public static final int USERNAME_MAX_LENGTH = 11;
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int EMAIL_ADDRESS_MAX_LENGTH = 64;
    public static final int PHONE_NO_MAX_LENGTH = 32;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int SURNAME_MAX_LENGTH = 50;
    public static final int SEX_MAX_LENGTH = 8;

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z]).{8,32}$";
    public static final String USERNAME_PATTERN = "^(?=.{3,11}$)[a-z0-9._]+$";

    private UserValidate() {}

    public static boolean validateUsernameLength(String username) throws UserException {
        validateNotNull(username, "username is null");
        validateStringLength(username.trim().toLowerCase(), USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH, "Username length must be between 3 and 11 characters");
        return true;
    }

    public static boolean validatePasswordLength(String password) throws UserException {
        validateNotNull(password, "password is null");
        validateStringLength(password, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH, "Password length must be between 8 and 32 characters");
        return true;
    }

    public static boolean validatePassword(String password) throws UserException {
        validateNotNull(password, "password is null");
        validatePattern(password, PASSWORD_PATTERN, "Password must contain at least one digit and one lowercase letter, and be between 8 and 32 characters long");
        return true;
    }

    public static boolean validateEmailLength(String email) throws UserException {
        validateNotNull(email, "email is null");
        validateStringLength(email, 1, EMAIL_ADDRESS_MAX_LENGTH, "Email length must be less than 64 characters");
        return true;
    }

    public static boolean validateEmailPattern(String email) throws UserException {
        validateNotNull(email, "email is null");
        validatePattern(email, EMAIL_PATTERN, "Email is not valid");
        return true;
    }

    public static boolean validateGender(String gender) throws UserException {
        validateNotNull(gender, "gender is null");
        if (!gender.trim().equalsIgnoreCase("male")
                && !gender.trim().equalsIgnoreCase("female")
                && !gender.trim().equalsIgnoreCase("")) {
            throw new UserException("Invalid gender");
        }
        return true;
    }

    public static boolean validateUniqueEmail(String email, String existingEmail) throws UserException {
        validateNotNull(email, "email is null");
        if (email.equals(existingEmail)) {
            throw new UserException("This email address is already being used");
        }
        return true;
    }

    public static boolean validateUniqueUsername(String username, String existingUsername) throws UserException {
        validateNotNull(username, "username is null");
        if (username.equals(existingUsername)) {
            throw new UserException("This username is already being used");
        }
        return true;
    }

    public static boolean validateUsernamePattern(String username) throws UserException {
        validateNotNull(username, "username is null");
        validatePattern(username, USERNAME_PATTERN, "Username is not valid");
        return true;
    }

    public static boolean validateNameLength(String name) throws UserException {
        validateNotNull(name, "name is null");
        validateStringLength(name.trim(), 1, NAME_MAX_LENGTH, "Name length must be less than 50 characters");
        return true;
    }

    public static boolean validateSurnameLength(String surname) throws UserException {
        validateNotNull(surname, "surname is null");
        validateStringLength(surname.trim(), 1, SURNAME_MAX_LENGTH, "Surname length must be less than 50 characters");
        return true;
    }

    public static boolean validateUser(User user) throws UserException {
        validateNotNull(user, "user is null");
        return validateEmailLength(user.getEmailAddress()) &&
                validateUsernameLength(user.getUid()) &&
                validateUsernamePattern(user.getUid()) &&
                validateEmailPattern(user.getEmailAddress()) &&
                validateGender(user.getGender());
    }

    public static void validateNotNull(Object value, String message) throws UserException {
        if (value == null) {
            throw new UserException(message);
        }
    }

    private static void validateStringLength(String value, int minLength, int maxLength, String message) throws UserException {
        if (value == null || value.trim().isEmpty() || value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new UserException(message);
        }
    }

    private static void validatePattern(String value, String pattern, String message) throws UserException {
        if (!Pattern.compile(pattern).matcher(value).matches()) {
            throw new UserException(message);
        }
    }
}