package com.project.usermanagement.user.infrastructure.mappers;
import com.project.usermanagement.user.domain.models.User;
import com.project.usermanagement.user.infrastructure.entities.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setUserName(userEntity.getUserName());
        user.setEmail(userEntity.getEmail());
        user.setActive(userEntity.isActive());
        return user;

    }
    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUserName(user.getUserName());
        userEntity.setEmail(user.getEmail());
        userEntity.setActive(user.isActive());
        return userEntity;
    }
}
