package com.fayardev.regms.commentservice.service;

import com.fayardev.regms.commentservice.client.UserGrpcClient;
import com.fayardev.regms.commentservice.dto.UserDto;
import com.fayardev.regms.commentservice.repository.UserRepository;
import com.fayardev.regms.commentservice.service.abstracts.IUserQueryHandler;
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

        return repository.countFollowee(user.getUuid());
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
        return repository.countFollowee(userId);
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

        return repository.findFollowee(user.getUuid());
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
        return repository.findFollowee(userId);
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
