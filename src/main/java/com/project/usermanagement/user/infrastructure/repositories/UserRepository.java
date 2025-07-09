package com.project.usermanagement.user.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.usermanagement.exceptions.EntityNotExistsException;
import com.project.usermanagement.shared.Constants;
import com.project.usermanagement.user.domain.models.User;
import com.project.usermanagement.user.domain.repositories.IUserRepository;
import com.project.usermanagement.user.infrastructure.entities.UserEntity;
import com.project.usermanagement.user.infrastructure.mappers.UserMapper;

@Repository
@Primary
public class UserRepository implements IUserRepository{

    @Autowired
    private IUserJpaRepository userRepository;

    /**
     * Get all users from database
     *
     * @return List Users in database.
     */
    @Override
    public List<User> get() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDomain).collect(Collectors.toList());
    }

    /**
     * Get a user by id
     *
     * @param id Unique identifier in UUID
     * @return User object.
     * @throws EntityNotExistsException if the user not exists
     */
    @Override
    public User getById(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new EntityNotExistsException(Constants.userNotFound);
        }
        return UserMapper.toDomain(user.get());
    }

    /**
     * Create a user model in database
     *
     * @param user model
     * @return Create object user.
     * @throws EntityExistsException if the user exists
     */
    @Override
    public User create(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserEntity response = userRepository.save(userEntity);
        return UserMapper.toDomain(response);
    }

    /**
     * Delete a user in database
     *
     * @param id Unique identifier in UUID
     */
    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
    
}
