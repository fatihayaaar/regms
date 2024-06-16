package com.fayardev.regms.userservice.controllers;

import com.fayardev.regms.userservice.services.UserQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/public/validate")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ValidateController {

    private final UserQueryHandler queryHandler;

    @GetMapping("/username/{username}")
    public ResponseEntity<Boolean> isThereUsername(@PathVariable String username) {
        return ResponseEntity.ok(queryHandler.getEntityByUsername(username) != null);
    }

    @GetMapping("/email-address/{email}")
    public ResponseEntity<Boolean> isThereEmail(@PathVariable String email) {
        return ResponseEntity.ok(queryHandler.getEntityByEmail(email) != null);
    }

    @GetMapping("/phoneNo/{phoneNo}")
    public ResponseEntity<Boolean> isTherePhoneNo(@PathVariable String phoneNo) {
        return ResponseEntity.ok(queryHandler.getEntityByPhoneNo(phoneNo) != null);
    }

}
