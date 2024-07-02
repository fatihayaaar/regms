package com.fayardev.regms.commentservice.config;

import com.fayardev.regms.commentservice.service.grpc.GrpcLikeService;
import com.netflix.appinfo.ApplicationInfoManager;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class GrpcConfig {

    private final GrpcLikeService grpcLikeService;

    @Value("${grpc.server.port:0}")
    private int port;

    private Server grpcServer;

    @Bean
    public Server grpcServer() throws IOException {
        this.grpcServer = ServerBuilder.forPort(port).addService(grpcLikeService).build().start();
        return this.grpcServer;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationInfoManager applicationInfoManager) {
        return args -> {
            if (grpcServer != null) {
                int actualPort = grpcServer.getPort();

                applicationInfoManager.getInfo().getMetadata().put("grpc.port", String.valueOf(actualPort));
                applicationInfoManager.registerAppMetadata(applicationInfoManager.getInfo().getMetadata());
            }
        };
    }
}