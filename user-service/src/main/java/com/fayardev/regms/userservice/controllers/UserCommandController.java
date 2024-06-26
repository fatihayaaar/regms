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

    private final UserCommandHandler handler;

    @PostMapping("/public/register")
    public ResponseEntity<String> register(@RequestBody UserDto user) {
        return ResponseEntity.ok(handler.saveEntity(user));
    }

    @PostMapping("/change-username")
    public ResponseEntity<Boolean> changeUsername(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeUsername(user));
    }

    @PostMapping("/change-email")
    public ResponseEntity<Boolean> changeEmailAddress(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeEmailAddress(user));
    }

    @PostMapping("/change-name")
    public ResponseEntity<Boolean> changeName(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeName(user));
    }

    @PostMapping("/change-surname")
    public ResponseEntity<Boolean> changeSurname(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeSurname(user));
    }

    @PostMapping("/change-confirm")
    public ResponseEntity<Boolean> changeConfirm(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeConfirm(user));
    }

    @PostMapping("/change-active")
    public ResponseEntity<Boolean> changeIsActive(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeIsActive(user));
    }

    @PostMapping("/change-verified")
    public ResponseEntity<Boolean> changeVerified(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeVerified(user));
    }

    @PostMapping("/change-gender")
    public ResponseEntity<Boolean> changeGender(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeGender(user));
    }

    @PostMapping("/change-birth-of-date")
    public ResponseEntity<Boolean> changeBirthOfDate(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeBirthOfDate(user));
    }

    @PostMapping("/change-avatar")
    public ResponseEntity<Boolean> changeAvatar(@RequestBody UserDto user) throws UserException {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.changeAvatar(user));
    }

    @PostMapping("/delete-avatar")
    public ResponseEntity<Boolean> deleteAvatar() throws UserException {
        return ResponseEntity.ok(handler.deleteAvatar(UserDto.builder().uuid(JwtUtils.getUserUUID()).build()));
    }

    @PatchMapping("/freeze")
    public ResponseEntity<Boolean> freeze(@RequestBody UserDto user) {
        user.setUuid(JwtUtils.getUserUUID());
        return ResponseEntity.ok(handler.freeze(user));
    }

}
