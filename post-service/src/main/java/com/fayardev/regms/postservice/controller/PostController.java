package com.fayardev.regms.postservice.controller;

import com.fayardev.regms.postservice.service.PostService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }
}
