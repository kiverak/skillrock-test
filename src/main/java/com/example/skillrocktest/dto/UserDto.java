package com.example.skillrocktest.dto;

import com.example.skillrocktest.model.RoleEnum;

import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String secondName,
        String lastName,
        String phoneNumber,
        String imageUrl,
        RoleEnum roleEnum
) {}
