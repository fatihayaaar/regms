package com.fayardev.regms.gatewayserver.config;

import com.fayardev.regms.gatewayserver.handler.CustomLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    final private CustomLogoutHandler customLogoutHandler;

    @Autowired
    SecurityConfig(CustomLogoutHandler customLogoutHandler) {
        this.customLogoutHandler = customLogoutHandler;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
                      //  .pathMatchers("/webjars/swagger-ui/**").hasRole("ADMIN")
                        .anyExchange()
                        .authenticated())
                .oauth2Login(withDefaults());
        http.logout(logoutSpec -> logoutSpec.logoutHandler(customLogoutHandler));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

}
