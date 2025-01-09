package com.emse.spring.serrato.mapper;

import com.emse.spring.serrato.api.UserCommand;
import com.emse.spring.serrato.model.UserEntity;
import com.emse.spring.serrato.record.User;

public class UserMapper {
    public static User toRecord(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword()
        );
    }

    public static UserEntity toEntity(UserCommand command) {
        if (command == null) {
            return null; // Gérer le cas où le UserCommand est null
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(command.username());
        entity.setPassword(command.password());

        return entity;
    }

}
