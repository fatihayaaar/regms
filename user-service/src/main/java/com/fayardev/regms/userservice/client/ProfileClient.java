package com.fayardev.regms.userservice.client;

import com.fayardev.regms.userservice.dtos.ProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "profile-service")
public interface ProfileClient {

    @PostMapping("/add")
    ResponseEntity<?> addProfile(@RequestBody ProfileDto profileDto);
}