package com.fayardev.regms.userservice.controllers;

import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.exceptions.UserException;
import com.fayardev.regms.userservice.services.UserCommandHandler;
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
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(commandHandler.saveEntity(userDto));
    }

    @PatchMapping("/change-username")
    public ResponseEntity<Boolean> changeUsername(@RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.ok(commandHandler.changeUsername(userDto));
    }

    @PatchMapping("/change-firstname")
    public ResponseEntity<Boolean> changeFirstname() {
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/change-surname")
    public ResponseEntity<Boolean> changeSurname() {
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/change-email-address")
    public ResponseEntity<Boolean> changeEmailAddress() {
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/change-photo")
    public ResponseEntity<Boolean> changePhoto() {
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/freeze")
    public ResponseEntity<Boolean> freeze() {
        return ResponseEntity.ok(true);
    }
}
