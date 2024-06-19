package com.fayardev.regms.userservice.client;

import com.fayardev.regms.userservice.dtos.UserFollowerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "follower-service")
public interface FollowerClient {

    @PostMapping("/v1/add")
    ResponseEntity<UserFollowerDto> createUser(@RequestParam UserFollowerDto userFollowerDto);
}
