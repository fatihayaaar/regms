package com.fayardev.regms.followservice.service;

import com.fayardev.regms.followservice.repository.FollowRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository repository;

    public FollowService(FollowRepository repository) {
        this.repository = repository;
    }
}
