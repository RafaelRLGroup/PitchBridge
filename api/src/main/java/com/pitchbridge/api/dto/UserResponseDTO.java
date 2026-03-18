package com.pitchbridge.api.dto;

import com.pitchbridge.api.model.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
