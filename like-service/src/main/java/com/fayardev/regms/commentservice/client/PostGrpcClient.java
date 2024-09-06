package com.fayardev.regms.commentservice.client;

import com.fayardev.postservice.post.PostRequest;
import com.fayardev.postservice.post.PostResponse;
import com.fayardev.postservice.post.PostServiceGrpc;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostGrpcClient {

    private final EurekaClient eurekaClient;
    private static final String POST_SERVICE_NAME = "post-service";

    private ManagedChannel getChannel(String grpcPort) {
        return ManagedChannelBuilder.forAddress(POST_SERVICE_NAME, Integer.parseInt(grpcPort))
                .usePlaintext()
                .build();
    }

    private String getGrpcPort() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(POST_SERVICE_NAME, false);
        return instanceInfo.getMetadata().get("grpc.port");
    }

    public boolean checkPostExists(String postId) {
        String grpcPort = getGrpcPort();
        ManagedChannel channel = getChannel(grpcPort);
        try {
            PostServiceGrpc.PostServiceBlockingStub stub = PostServiceGrpc.newBlockingStub(channel);

            PostRequest request = PostRequest.newBuilder().setPostId(postId).build();
            PostResponse response = stub.checkPostExists(request);
            return response.getExists();
        } finally {
            if (channel != null && !channel.isShutdown()) {
                channel.shutdown();
            }
        }
    }
}