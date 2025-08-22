package com.example.skillrocktest.service;

import com.example.skillrocktest.dto.CreateUserRequest;
import com.example.skillrocktest.dto.UpdateUserRequest;
import com.example.skillrocktest.dto.UserDto;
import com.example.skillrocktest.dto.UserMapper;
import com.example.skillrocktest.exception.UserNotFoundException;
import com.example.skillrocktest.model.Role;
import com.example.skillrocktest.model.User;
import com.example.skillrocktest.repo.RoleRepository;
import com.example.skillrocktest.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    @CachePut(value = "users", key = "#result.id")
    public UserDto createUser(CreateUserRequest request) {
        User user = new User();
        user.setFirstName(request.firstName());
        user.setSecondName(request.secondName());
        user.setLastName(request.lastName());
        user.setPhoneNumber(request.phoneNumber());
        user.setImageUrl(request.imageUrl());

        Role role = new Role();
        role.setRoleName(request.role());
        user.setRole(role);

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "#userId")
    public UserDto findById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    @CachePut(value = "users", key = "#userId")
    public UserDto updateUser(UUID userId, UpdateUserRequest request) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Cannot update.User with ID " + userId + " not found."));

        existingUser.setFirstName(request.firstName());
        existingUser.setSecondName(request.secondName());
        existingUser.setLastName(request.lastName());
        existingUser.setPhoneNumber(request.phoneNumber());
        existingUser.setImageUrl(request.imageUrl());
        Role oldRole = existingUser.getRole();

        Role role = new Role();
        role.setRoleName(request.roleEnum());
        existingUser.setRole(role);

        User updatedUser = userRepository.save(existingUser);
        roleRepository.delete(oldRole);
        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#userId")
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Cannot delete. User with ID " + userId + " not found.");
        }
        userRepository.deleteById(userId);
    }
}
