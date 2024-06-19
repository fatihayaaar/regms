package com.fayardev.regms.followservice.service;

import com.fayardev.regms.followservice.dto.UserDto;
import com.fayardev.regms.followservice.entity.User;
import com.fayardev.regms.followservice.repository.UserRepository;
import com.fayardev.regms.followservice.service.abstracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService<UserDto> {

    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        repository.save(modelMapper.map(userDto, User.class));
        return userDto;
    }

    @Transactional
    @Override
    public boolean followUser(String id, String followId) {
        Optional<User> user = repository.findById(id);
        Optional<User> followUser = repository.findById(followId);

        if (user.isPresent() && followUser.isPresent()) {
            user.get().getFollowing().add(followUser.get());
            repository.save(user.get());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean unfollowUser(String id, String unfollowId) {
        Optional<User> user = repository.findById(id);
        Optional<User> unfollowUser = repository.findById(unfollowId);

        if (user.isPresent() && unfollowUser.isPresent()) {
            user.get().getFollowing().remove(unfollowUser.get());
            repository.save(user.get());
            return true;
        }
        return false;
    }

    @Override
    public Iterable<UserDto> getAll() {
        return repository.findAll().stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }
}
