package com.fayardev.regms.followservice.client;

import com.fayardev.userservice.user.UserRequest;
import com.fayardev.userservice.user.UserResponse;
import com.fayardev.userservice.user.UserServiceGrpc;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGrpcClient {

    private final EurekaClient eurekaClient;
    private static final String USER_SERVICE_NAME = "user-service";

    private ManagedChannel getChannel(String grpcPort) {
        return ManagedChannelBuilder.forAddress("localhost", Integer.parseInt(grpcPort))
                .usePlaintext()
                .build();
    }

    private String getGrpcPort() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(USER_SERVICE_NAME, false);
        return instanceInfo.getMetadata().get("grpc.port");
    }

    public UserResponse getUserByUuid(String uuid) {
        String grpcPort = getGrpcPort();
        ManagedChannel channel = getChannel(grpcPort);
        try {
            UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

            UserRequest request = UserRequest.newBuilder().setUuid(uuid).build();
            return stub.getUserByUuid(request);
        } finally {
            if (channel != null && !channel.isShutdown()) {
                channel.shutdown();
            }
        }
    }

    public UserResponse getUserByUsername(String username) {
        String grpcPort = getGrpcPort();
        ManagedChannel channel = getChannel(grpcPort);
        try {
            UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

            UserRequest request = UserRequest.newBuilder().setUid(username).build();
            return stub.getUserByUid(request);
        } finally {
            if (channel != null && !channel.isShutdown()) {
                channel.shutdown();
            }
        }
    }
}