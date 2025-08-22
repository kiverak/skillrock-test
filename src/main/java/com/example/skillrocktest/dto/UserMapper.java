package com.example.skillrocktest.dto;

import com.example.skillrocktest.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getSecondName(), user.getLastName(),
                user.getPhoneNumber(), user.getImageUrl(), user.getRole().getRoleName());
    }
}
