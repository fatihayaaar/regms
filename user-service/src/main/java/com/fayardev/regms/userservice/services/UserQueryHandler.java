package com.fayardev.regms.userservice.services;

import com.fayardev.regms.userservice.dtos.UserDto;
import com.fayardev.regms.userservice.entities.User;
import com.fayardev.regms.userservice.repositories.UserRepository;
import com.fayardev.regms.userservice.services.abstracts.IUserQueryHandler;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQueryHandler implements IUserQueryHandler<UserDto> {

    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDto> getEntities() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public List<UserDto> searchUsers(String value) {
        List<User> users = repository.searchUsers(value);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public UserDto getEntityByEmail(String email) {
        return repository.getUserByEmailAddress(email)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElse(null);
    }

    @Override
    public UserDto getEntityByUsername(String username) {
        return repository.getUserByUid(username)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElse(null);
    }

    @Override
    public UserDto getEntityByPhoneNo(String phoneNo) {
        return repository.getUserByPhoneNo(phoneNo)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElse(null);
    }
}