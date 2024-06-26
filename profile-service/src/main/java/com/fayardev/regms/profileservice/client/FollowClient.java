package com.fayardev.regms.profileservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "follow-service")
public interface FollowClient {

    @GetMapping("/v1/{username}/is-following")
    boolean isFollowing(@PathVariable String username);

    @GetMapping("/v1/followee-count")
    int getFolloweeCount(@RequestParam String username);

    @GetMapping("/v1/follower-count")
    int getFollowerCount(@RequestParam String username);

    @GetMapping("/v1/my-followee-count")
    int getMyFolloweeCount();

    @GetMapping("/v1/my-follower-count")
    int getMyFollowerCount();
}
