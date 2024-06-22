package com.fayardev.regms.userservice.controllers;

import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.exceptions.UserException;
import com.fayardev.regms.userservice.services.UserCommandHandler;
import com.fayardev.regms.userservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public final class UserCommandController {

    private final UserCommandHandler commandHandler;

    @PostMapping("/public/register")
    public ResponseEntity<String> register(@RequestBody UserDto user) {
        return ResponseEntity.ok(commandHandler.saveEntity(user));
    }

    @PostMapping("/change-username")
    public ResponseEntity<Boolean> changeUsername(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeUsername(user));
    }

    @PostMapping("/change-email")
    public ResponseEntity<Boolean> changeEmailAddress(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeEmailAddress(user));
    }

    @PostMapping("/change-name")
    public ResponseEntity<Boolean> changeName(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeName(user));
    }

    @PostMapping("/change-surname")
    public ResponseEntity<Boolean> changeSurname(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeSurname(user));
    }

    @PostMapping("/change-confirm")
    public ResponseEntity<Boolean> changeConfirm(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeConfirm(user));
    }

    @PostMapping("/change-active")
    public ResponseEntity<Boolean> changeIsActive(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeIsActive(user));
    }

    @PostMapping("/change-verified")
    public ResponseEntity<Boolean> changeVerified(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeVerified(user));
    }

    @PostMapping("/change-gender")
    public ResponseEntity<Boolean> changeGender(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeGender(user));
    }

    @PostMapping("/change-birth-of-date")
    public ResponseEntity<Boolean> changeBirthOfDate(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeBirthOfDate(user));
    }

    @PostMapping("/change-jpeg-photo")
    public ResponseEntity<Boolean> changeJpegPhoto(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.changeJpegPhoto(user));
    }

    @PostMapping("/delete-jpeg-photo")
    public ResponseEntity<Boolean> deleteJpegPhoto() throws UserException {
        return ResponseEntity.ok(commandHandler.deleteJpegPhoto(UserDto.builder().uuid(JwtUtils.getUserUUID()).build()));
    }

    @PatchMapping("/freeze")
    public ResponseEntity<Boolean> freeze(@RequestBody UserDto user) {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(commandHandler.freeze(user));
    }

}
