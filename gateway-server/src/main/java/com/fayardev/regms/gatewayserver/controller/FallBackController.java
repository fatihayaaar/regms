package com.fayardev.regms.gatewayserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {
    @GetMapping("/userFallback")
    public ResponseEntity<String> userFallBack(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("We are facing a problem");
    }
}
