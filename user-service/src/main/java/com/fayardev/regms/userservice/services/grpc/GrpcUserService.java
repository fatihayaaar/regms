package com.fayardev.regms.userservice.services.grpc;

import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.repositories.UserRepository;
import com.fayardev.userservice.user.UserRequest;
import com.fayardev.userservice.user.UserResponse;
import com.fayardev.userservice.user.UserServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GrpcUserService extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository repository;

    public GrpcUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getUserByUid(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        Optional<User> user = repository.getUserByUid(request.getUid());

        if (user.isPresent()) {
            User localUser = user.get();

            UserResponse response = UserResponse.newBuilder()
                    .setUid(request.getUid())
                    .setName(localUser.getName())
                    .setSurname(localUser.getSurname())
                    .setEmailAddress(localUser.getEmailAddress())
                    .setConfirm(localUser.isConfirm())
                    .setIsActive(localUser.isActive())
                    .setVerified(localUser.isVerified())
                    .setPhoneNo(localUser.getPhoneNo())
                    .setPassword(localUser.getPassword())
                    .setGender(localUser.getGender())
                    .setBirthOfDate(localUser.getBirthOfDate())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusRuntimeException(Status.NOT_FOUND));
        }
    }
}
