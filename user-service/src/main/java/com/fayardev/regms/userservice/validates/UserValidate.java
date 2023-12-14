package com.fayardev.regms.userservice.validates;

import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;
import com.fayardev.regms.userservice.exceptions.enums.ErrorComponents;
import com.fayardev.regms.userservice.exceptions.enums.Errors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidate {

    public static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z]).{8,16}$";
    public static final String USERNAME_PATTERN = "^(?=.{3,11}$)[a-z0-9._]+$";

    public static boolean strUsernameLengthValidate(String username) throws UserException {
        if (username == null) {
            throw new UserException("null data", Errors.NOT_NULL_ERROR, ErrorComponents.USERNAME);
        }
        if (!username.trim().isEmpty()) {
            username = username.trim().toLowerCase();
            if (!username.trim().isEmpty() && username.trim().length() <= User.USERNAME_MAX_LENGTH) {
                return true;
            } else throw new UserException("32 karakterden uzun", Errors.MAX_VALUE_LENGTH, ErrorComponents.USERNAME);
        } else
            throw new UserException("username cannot be blank", Errors.NOT_NULL_ERROR, ErrorComponents.USERNAME);
    }

    public static boolean passwordLengthValidate(String password) throws UserException {
        if (password == null) {
            throw new UserException("null data", Errors.NOT_NULL_ERROR, ErrorComponents.PASSWORD);
        }
        if (!password.equals("")) {
            if (password.trim().length() >= User.PASSWORD_MIN_LENGTH
                    && password.length() <= User.PASSWORD_MAX_LENGTH)
                return true;
            else
                throw new UserException("character length exceeded the limit", Errors.LENGTH_LIMIT, ErrorComponents.PASSWORD);
        } else throw new UserException("null data", Errors.NOT_NULL_ERROR, ErrorComponents.PASSWORD);
    }

    public static boolean passwordValidate(String pass) throws UserException {
        if (pass == null) {
            throw new UserException("null data", Errors.NOT_NULL_ERROR, ErrorComponents.PASSWORD);
        }
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(pass);
        if (matcher.matches()) {
            return true;
        } else throw new UserException("password is unusable", Errors.REGEX, ErrorComponents.PASSWORD);
    }

    public static boolean emailLengthValidate(String email) throws UserException {
        if (email == null) {
            throw new UserException("null data", Errors.NOT_NULL_ERROR, ErrorComponents.EMAIL);
        }
        if (email.trim().length() <= User.EMAIL_ADDRESS_MAX_LENGTH && email.trim().length() > 0) {
            return true;
        } else
            throw new UserException("character length exceeded the limit", Errors.MAX_VALUE_LENGTH, ErrorComponents.EMAIL);
    }

    public static boolean emailRegexValidate(String email) throws UserException {
        if (email == null) {
            throw new UserException("null data", Errors.NOT_NULL_ERROR, ErrorComponents.EMAIL);
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else throw new UserException("email is unusable", Errors.REGEX, ErrorComponents.EMAIL);
    }

    public static boolean genderValidate(String gender) throws UserException {
        if (gender == null) {
            throw new UserException("null data", Errors.NOT_NULL_ERROR, ErrorComponents.GENDER);
        }
        if (gender.trim().equalsIgnoreCase("male")
                || gender.trim().equalsIgnoreCase("female")) {
            return true;
        } else {
            throw new UserException("invalid gender", Errors.GENDER, ErrorComponents.GENDER);
        }
    }

    public static boolean emailEquals(String str, String value, ErrorComponents errorComponent) throws UserException {
        if (!str.equals(value)) {
            return true;
        } else throw new UserException("this email address is already being used", Errors.USED_EMAIL, errorComponent);
    }

    public static boolean usernameEquals(String str, String value, ErrorComponents errorComponent) throws UserException {
        if (!str.equals(value)) {
            return true;
        } else throw new UserException("this username is already being used", Errors.USED_USERNAME, errorComponent);
    }

    public static boolean usernameRegexValidate(String username) throws UserException {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        if (matcher.matches()) {
            return true;
        } else throw new UserException("username is unusable", Errors.REGEX, ErrorComponents.USERNAME);
    }

    public static boolean userValidate(User user) throws UserException {
        if (user == null) {
            throw new UserException("null data", Errors.NOT_NULL_ERROR, ErrorComponents.USER);
        }
        if (UserValidate.emailLengthValidate(user.getEmailAddress())) {
            if (UserValidate.strUsernameLengthValidate(user.getUid())) {
                if (UserValidate.usernameRegexValidate(user.getUid())) {
                    if (UserValidate.emailRegexValidate(user.getEmailAddress())) {
                        return UserValidate.genderValidate(user.getGender());
                    } else return false;
                } else return false;
            } else return false;
        }
        return false;
    }

}