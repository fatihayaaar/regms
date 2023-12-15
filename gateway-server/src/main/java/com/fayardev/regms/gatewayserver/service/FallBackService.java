package com.fayardev.regms.gatewayserver.service;

import org.springframework.stereotype.Service;

@Service
public class FallBackService {

    public String yourHystrixEnabledMethod() {
        return "Success";
    }

    public String fallbackMethod() {
        return "Fallback response";
    }

}
