package com.fayardev.regms.postservice.controller;

import com.fayardev.regms.postservice.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContentController {

    private final ContentService service;
}
