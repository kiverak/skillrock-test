package com.example.skillrocktest.dto;

import com.example.skillrocktest.model.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank String firstName,
        @NotBlank String secondName,
        @NotBlank String lastName,
        @NotBlank @Size(min = 12, max = 20) String phoneNumber,
        String imageUrl,
        @NotNull RoleEnum role
) {}
