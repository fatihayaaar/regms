package com.fayardev.regms.postservice.controller;

import com.fayardev.regms.postservice.service.ContentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    private final ContentService service;

    public ContentController(ContentService service) {
        this.service = service;
    }
}
