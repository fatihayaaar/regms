package com.fayardev.regms.userservice.config;

import com.fayardev.regms.userservice.services.grpc.GrpcUserService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcConfig {

    @Bean
    public Server grpcServer(GrpcUserService service) throws IOException {
        ServerBuilder<?> server = ServerBuilder
                .forPort(9090)
                .addService(service);

        return server.build();
    }
}
