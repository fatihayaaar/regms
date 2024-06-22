package com.fayardev.regms.userservice.services.grpc;

import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.repositories.UserRepository;
import com.fayardev.userservice.user.UserRequest;
import com.fayardev.userservice.user.UserResponse;
import com.fayardev.userservice.user.UserServiceGrpc;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GrpcUserService extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository repository;

    @Override
    public void getUserByUuid(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        Optional<User> user = repository.getUserByUuid(request.getUuid());

        if (user.isPresent()) {
            User localUser = user.get();
            UserResponse.Builder responseBuilder = UserResponse.newBuilder().setUuid(request.getUuid());

            responseBuilder.setUid(localUser.getUid());

            if (localUser.getName() != null) {
                responseBuilder.setName(localUser.getName());
            }
            if (localUser.getSurname() != null) {
                responseBuilder.setSurname(localUser.getSurname());
            }
            if (localUser.getEmailAddress() != null) {
                responseBuilder.setEmailAddress(localUser.getEmailAddress());
            }
            responseBuilder.setConfirm(localUser.isConfirm());
            responseBuilder.setIsActive(localUser.isActive());
            responseBuilder.setVerified(localUser.isVerified());
            if (localUser.getGender() != null) {
                responseBuilder.setGender(localUser.getGender());
            }
            if (localUser.getBirthOfDate() != null) {
                responseBuilder.setBirthOfDate(localUser.getBirthOfDate());
            }
            if (localUser.getJpegPhoto() != null) {
                responseBuilder.setJpegPhoto(ByteString.copyFrom(localUser.getJpegPhoto()));
            }

            UserResponse response = responseBuilder.build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusRuntimeException(Status.NOT_FOUND));
        }
    }
}