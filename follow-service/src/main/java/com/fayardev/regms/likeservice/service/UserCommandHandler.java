package com.fayardev.regms.likeservice.service;

import com.fayardev.regms.likeservice.client.UserGrpcClient;
import com.fayardev.regms.likeservice.dto.UserDto;
import com.fayardev.regms.likeservice.entity.User;
import com.fayardev.regms.likeservice.repository.UserRepository;
import com.fayardev.regms.likeservice.service.abstracts.IUserCommandHandler;
import com.fayardev.userservice.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandHandler implements IUserCommandHandler<UserDto> {

    private final UserRepository repository;
    private final UserGrpcClient userClient;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        repository.save(modelMapper.map(userDto, User.class));
        return userDto;
    }

    @Transactional
    @Override
    public boolean followUser(String username, String userId) {
        Optional<User> user = repository.findById(userId);
        UserResponse otherUser = userClient.getUserByUsername(username);

        if (user.isPresent() && otherUser != null) {
            user.get().getFollowing().add(User.builder().id(otherUser.getUuid()).build());
            repository.save(user.get());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean unfollowUser(String username, String userId) {
        UserResponse otherUser = userClient.getUserByUsername(username);

        if (otherUser != null) {
            return repository.unfollow(userId, otherUser.getUuid());
        }
        return false;
    }

    @Override
    public boolean isFollowing(String userId, String username) {
        UserResponse followingUser = userClient.getUserByUsername(username);

        return repository.isFollowing(userId, followingUser.getUuid());
    }
}
