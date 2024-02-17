package com.fayardev.regms.followservice.controller;

import com.fayardev.regms.followservice.service.FollowService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    private final FollowService service;

    public FollowController(FollowService service) {
        this.service = service;
    }
}
