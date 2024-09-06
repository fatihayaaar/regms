package com.fayardev.regms.postservice.client;

import com.fayardev.commentservice.comment.CommentCountRequest;
import com.fayardev.commentservice.comment.CommentCountResponse;
import com.fayardev.commentservice.comment.CommentServiceGrpc;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentGrpcClient {

    private final EurekaClient eurekaClient;
    private static final String COMMENT_SERVICE_NAME = "comment-service";

    private ManagedChannel getChannel(String grpcPort) {
        return ManagedChannelBuilder.forAddress(COMMENT_SERVICE_NAME, Integer.parseInt(grpcPort))
                .usePlaintext()
                .build();
    }

    private String getGrpcPort() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(COMMENT_SERVICE_NAME, false);
        return instanceInfo.getMetadata().get("grpc.port");
    }

    public CommentCountResponse getCommentCount(String postId) {
        String grpcPort = getGrpcPort();
        ManagedChannel channel = getChannel(grpcPort);
        try {
            CommentServiceGrpc.CommentServiceBlockingStub stub = CommentServiceGrpc.newBlockingStub(channel);

            CommentCountRequest request = CommentCountRequest.newBuilder().setPostId(postId).build();
            return stub.getCommentsCount(request);
        } finally {
            if (channel != null && !channel.isShutdown()) {
                channel.shutdown();
            }
        }
    }
}