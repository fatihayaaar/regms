package com.fayardev.regms.userservice.interceptor;

import com.fayardev.regms.userservice.util.JwtUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        if (!shouldExcludeAuthorizationHeader(template.url())) {
            String token = JwtUtils.getBearerToken();
            if (token != null) {
                template.header("Authorization", "Bearer " + token);
            }
        }
    }

    private boolean shouldExcludeAuthorizationHeader(String url) {
        return url.startsWith("/v1/feign/") || url.startsWith("/v1/public/");
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignClientInterceptor();
    }
}