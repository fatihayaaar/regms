package com.fayardev.regms.likeservice.service.grpc;

import com.fayardev.likeservice.like.*;
import com.fayardev.regms.likeservice.repository.LikeRepository;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GrpcLikeService extends LikeServiceGrpc.LikeServiceImplBase {

    private final LikeRepository repository;

    @Override
    public void getIsLike(LikeRequest request, StreamObserver<LikeResponse> responseObserver) {
        try {
            boolean exists = repository.isPostLikedByUser(request.getPostId(), request.getUserId());

            LikeResponse response = LikeResponse.newBuilder().setIsLike(exists).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(Status.INTERNAL.withDescription(e.getMessage())));
        }
    }

    @Override
    public void getLikesCount(LikeCountRequest request, StreamObserver<LikeCountResponse> responseObserver) {
        try {
            long exists = repository.countLikesByPostId(request.getPostId());

            LikeCountResponse response = LikeCountResponse.newBuilder().setLikeCount((int) exists).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(Status.INTERNAL.withDescription(e.getMessage())));
        }
    }
}