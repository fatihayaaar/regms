package com.fayardev.regms.userservice.controllers;

import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.services.UserQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryHandler handler;

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> search(@RequestParam String query) {
        return ResponseEntity.ok(handler.searchUsers(query));
    }
}
