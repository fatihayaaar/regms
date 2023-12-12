package com.fayardev.regms.userservice.controllers;

import com.fayardev.regms.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validate")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ValidateController {

    private final UserService userService;

    @Autowired
    public ValidateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Boolean> isThereUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getEntityByUsername(username) != null);
    }

    @GetMapping("/email-address/{email}")
    public ResponseEntity<Boolean> isThereEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getEntityByEmail(email) != null);
    }

    @GetMapping("/phoneNo/{phoneNo}")
    public ResponseEntity<Boolean> isTherePhoneNo(@PathVariable String phoneNo) {
        return ResponseEntity.ok(userService.getEntityByPhoneNo(phoneNo) != null);
    }

}
