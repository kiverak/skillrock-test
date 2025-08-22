package com.example.skillrocktest.service;

import com.example.skillrocktest.dto.CreateUserRequest;
import com.example.skillrocktest.dto.UpdateUserRequest;
import com.example.skillrocktest.dto.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto createUser(CreateUserRequest request);

    UserDto findById(UUID userId);

    UserDto updateUser(UUID userId, UpdateUserRequest request);

    void deleteUser(UUID userId);
}
