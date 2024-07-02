package com.fayardev.regms.commentservice.service.grpc;

import com.fayardev.commentservice.comment.*;
import com.fayardev.regms.commentservice.repository.CommentRepository;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GrpcCommentService extends CommentServiceGrpc.CommentServiceImplBase {

    private final CommentRepository repository;

    @Override
    public void getCommentsCount(CommentCountRequest request, StreamObserver<CommentCountResponse> responseObserver) {
        try {
            long exists = repository.countCommentsByPostId(request.getPostId());

            CommentCountResponse response = CommentCountResponse.newBuilder().setCommentCount((int) exists).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(Status.INTERNAL.withDescription(e.getMessage())));
        }
    }
}