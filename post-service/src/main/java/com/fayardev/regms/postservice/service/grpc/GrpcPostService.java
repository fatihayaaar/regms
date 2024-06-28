package com.fayardev.regms.postservice.service.grpc;

import com.fayardev.postservice.post.PostRequest;
import com.fayardev.postservice.post.PostResponse;
import com.fayardev.postservice.post.PostServiceGrpc;
import com.fayardev.regms.postservice.repository.PostRepository;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GrpcPostService extends PostServiceGrpc.PostServiceImplBase {

    private final PostRepository repository;

    @Override
    public void checkPostExists(PostRequest request, StreamObserver<PostResponse> responseObserver) {
        try {
            boolean exists = repository.existsById(request.getPostId());
            PostResponse response = PostResponse.newBuilder().setExists(exists).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(Status.INTERNAL.withDescription(e.getMessage())));
        }
    }
}