package com.fayardev.regms.userservice.services.grpc;

import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.repositories.UserRepository;
import com.fayardev.userservice.user.UserRequest;
import com.fayardev.userservice.user.UserResponse;
import com.fayardev.userservice.user.UserServiceGrpc;
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
        handleUserResponse(responseObserver, user);
    }

    @Override
    public void getUserByUid(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        Optional<User> user = repository.getUserByUid(request.getUid());
        handleUserResponse(responseObserver, user);
    }

    private void handleUserResponse(StreamObserver<UserResponse> responseObserver, Optional<User> user) {
        if (user.isPresent()) {
            UserResponse response = buildUserResponse(user.get());
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusRuntimeException(Status.NOT_FOUND.withDescription("User not found")));
        }
    }

    private UserResponse buildUserResponse(User user) {
        UserResponse.Builder responseBuilder = UserResponse.newBuilder()
                .setUuid(user.getUuid())
                .setUid(user.getUid())
                .setName(Optional.ofNullable(user.getName()).orElse(""))
                .setSurname(Optional.ofNullable(user.getSurname()).orElse(""))
                .setEmailAddress(Optional.ofNullable(user.getEmailAddress()).orElse(""))
                .setConfirm(user.isConfirm())
                .setIsActive(user.isActive())
                .setVerified(user.isVerified())
                .setGender(Optional.ofNullable(user.getGender()).orElse(""))
                .setBirthOfDate(Optional.ofNullable(user.getBirthOfDate()).orElse(""))
                .setAvatar(Optional.ofNullable(user.getAvatar()).orElse(""));

        return responseBuilder.build();
    }
}