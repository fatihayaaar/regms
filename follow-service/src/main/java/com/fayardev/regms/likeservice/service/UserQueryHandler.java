package com.fayardev.regms.likeservice.service;

import com.fayardev.regms.likeservice.client.UserGrpcClient;
import com.fayardev.regms.likeservice.dto.UserDto;
import com.fayardev.regms.likeservice.repository.UserRepository;
import com.fayardev.regms.likeservice.service.abstracts.IUserQueryHandler;
import com.fayardev.userservice.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryHandler implements IUserQueryHandler<UserDto> {

    private final UserRepository repository;
    private final UserGrpcClient userClient;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public int getFolloweeCount(String username) {
        UserResponse user = userClient.getUserByUsername(username);

        return repository.countFollowees(user.getUuid());
    }

    @Override
    @Transactional(readOnly = true)
    public int getFollowerCount(String username) {
        UserResponse user = userClient.getUserByUsername(username);

        return repository.countFollowers(user.getUuid());
    }

    @Override
    @Transactional(readOnly = true)
    public int getFolloweeCountById(String userId) {
        return repository.countFollowees(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public int getFollowerCountById(String userId) {
        return repository.countFollowers(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getFollowees(String username) {
        UserResponse user = userClient.getUserByUsername(username);

        return repository.findFollowees(user.getUuid());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getFollowers(String username) {
        UserResponse user = userClient.getUserByUsername(username);

        return repository.findFollowers(user.getUuid());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getFolloweesById(String userId) {
        return repository.findFollowees(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getFollowersById(String userId) {
        return repository.findFollowers(userId);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<UserDto> getAll() {
        return repository.findAll().stream().map(u -> modelMapper.map(u, UserDto.class)).toList();
    }
}
