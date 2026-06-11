package com.example.crudapi.user;

import com.example.crudapi.common.GenericMapper;
import org.springframework.stereotype.Component;

/**
 * Hand-written mapper (kept manual on purpose for a small project;
 * MapStruct would be the next step, see README).
 */
@Component
public class UserMapper implements GenericMapper<User, UserDto> {

    @Override
    public UserDto toDto(User entity) {
        return new UserDto(entity.getId(), entity.getName(), entity.getEmail());
    }

    @Override
    public User toEntity(UserDto dto) {
        User user = new User(dto.getName(), dto.getEmail());
        user.setId(dto.getId());
        return user;
    }
}
