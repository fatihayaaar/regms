package com.fayardev.regms.userservice.validates;

import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;

import java.util.regex.Pattern;

public class UserValidate {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z]).{8,16}$";
    public static final String USERNAME_PATTERN = "^(?=.{3,11}$)[a-z0-9._]+$";

    public static boolean validateUsernameLength(String username) throws UserException {
        validateNotNull(username, "username is null");
        validateStringLength(username.trim().toLowerCase(), 1, User.USERNAME_MAX_LENGTH, "32 karakterden uzun");
        return true;
    }

    public static boolean validatePasswordLength(String password) throws UserException {
        validateNotNull(password, "password is null");
        validateStringLength(password, User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH, "character length exceeded the limit");
        return true;
    }

    public static boolean validatePassword(String password) throws UserException {
        validateNotNull(password, "password is null");
        validatePattern(password, PASSWORD_PATTERN, "password is unusable");
        return true;
    }

    public static boolean validateEmailLength(String email) throws UserException {
        validateNotNull(email, "email is null");
        validateStringLength(email, 1, User.EMAIL_ADDRESS_MAX_LENGTH, "character length exceeded the limit");
        return true;
    }

    public static boolean validateEmailPattern(String email) throws UserException {
        validateNotNull(email, "null data");
        validatePattern(email, EMAIL_PATTERN, "email is unusable");
        return true;
    }

    public static boolean validateGender(String gender) throws UserException {
        if (!gender.trim().equalsIgnoreCase("male")
                && !gender.trim().equalsIgnoreCase("female")
                && !gender.trim().equalsIgnoreCase("")) {
            throw new UserException("invalid gender");
        }
        return true;
    }

    public static boolean validateUniqueEmail(String email, String existingEmail) throws UserException {
        if (email.equals(existingEmail)) {
            throw new UserException("this email address is already being used");
        }
        return true;
    }

    public static boolean validateUniqueUsername(String username, String existingUsername) throws UserException {
        if (username.equals(existingUsername)) {
            throw new UserException("this username is already being used");
        }
        return true;
    }

    public static boolean validateUsernamePattern(String username) throws UserException {
        validatePattern(username, USERNAME_PATTERN, "username is unusable");
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