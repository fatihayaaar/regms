package com.fayardev.regms.postservice.client;

import com.fayardev.likeservice.like.*;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeGrpcClient {

    private final EurekaClient eurekaClient;
    private static final String LIKE_SERVICE_NAME = "like-service";

    private ManagedChannel getChannel(String grpcPort) {
        return ManagedChannelBuilder.forAddress("localhost", Integer.parseInt(grpcPort))
                .usePlaintext()
                .build();
    }

    private String getGrpcPort() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(LIKE_SERVICE_NAME, false);
        return instanceInfo.getMetadata().get("grpc.port");
    }

    public LikeResponse getIsLike(String userId, String postId) {
        String grpcPort = getGrpcPort();
        ManagedChannel channel = getChannel(grpcPort);
        try {
            LikeServiceGrpc.LikeServiceBlockingStub stub = LikeServiceGrpc.newBlockingStub(channel);

            LikeRequest request = LikeRequest.newBuilder().setPostId(postId).setUserId(userId).build();
            return stub.getIsLike(request);
        } finally {
            if (channel != null && !channel.isShutdown()) {
                channel.shutdown();
            }
        }
    }

    public LikeCountResponse getLikesCount(String postId) {
        String grpcPort = getGrpcPort();
        ManagedChannel channel = getChannel(grpcPort);
        try {
            LikeServiceGrpc.LikeServiceBlockingStub stub = LikeServiceGrpc.newBlockingStub(channel);

            LikeCountRequest request = LikeCountRequest.newBuilder().setPostId(postId).build();
            return stub.getLikesCount(request);
        } finally {
            if (channel != null && !channel.isShutdown()) {
                channel.shutdown();
            }
        }
    }
}