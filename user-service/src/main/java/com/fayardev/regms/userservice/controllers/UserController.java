package com.fayardev.regms.userservice.controllers;

import com.fayardev.regms.userservice.dtos.ChangeUserDetailsDto;
import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.exceptions.UserException;
import com.fayardev.regms.userservice.services.UserService;
import com.fayardev.regms.userservice.validates.UserValidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public final class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserDto userDto) throws Exception {
        if (!UserValidate.passwordLengthValidate(userDto.getPassword())) {
            if (!UserValidate.passwordValidate(userDto.getPassword())) {
                return ResponseEntity.ok(false);
            }
        }
        return ResponseEntity.ok(userService.saveEntity(modelMapper.map(userDto, User.class)));
    }

    @PostMapping("/change-username")
    public ResponseEntity<?> changeUsername(@RequestBody @Valid ChangeUserDetailsDto changeUsernameDto) throws UserException {
        return ResponseEntity.ok(userService.changeUsername(modelMapper.map(changeUsernameDto, User.class)));
    }

    @PostMapping("/change-firstname")
    public ResponseEntity<?> changeFirstname() {
        return ResponseEntity.ok(true);
    }

    @PostMapping("/change-surname")
    public ResponseEntity<?> changeSurname() {
        return ResponseEntity.ok(true);
    }

    @PostMapping("/change-email-address")
    public ResponseEntity<?> changeEmailAddress() {
        return ResponseEntity.ok(true);
    }

    @PostMapping("/change-photo")
    public ResponseEntity<?> changePhoto() {
        return ResponseEntity.ok(true);
    }

    @PostMapping("/freeze")
    public ResponseEntity<?> freeze() {
        return ResponseEntity.ok(true);
    }
}
