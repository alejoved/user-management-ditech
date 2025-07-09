package com.project.usermanagement.user.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.usermanagement.exceptions.EntityNotExistsException;
import com.project.usermanagement.shared.Constants;
import com.project.usermanagement.user.application.interfaces.IUserGetUseCase;
import com.project.usermanagement.user.domain.models.User;
import com.project.usermanagement.user.domain.repositories.IUserRepository;

@Service
public class UserGetUseCase implements IUserGetUseCase {

    @Autowired
    private IUserRepository userRepository;

    /**
     * Get all users from the database
     *
     * @return List Users model.
     */
    @Override
    public List<User> execute() {
        List<User> users = userRepository.get();
        return users;
    }

    /**
     * Delete a user in database
     *
     * @param id Unique identifier in UUID
     * @return User found in database.
     * @throws EntityNotExistsException if the user not exists
     */
    @Override
    public User executeById(UUID id) {
        User user = userRepository.getById(id);
        if (user == null){
            throw new EntityNotExistsException(Constants.userNotFound);
        }
        return user;
    }
    
}
