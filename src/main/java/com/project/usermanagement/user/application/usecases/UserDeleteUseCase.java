package com.project.usermanagement.user.application.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.usermanagement.exceptions.EntityNotExistsException;
import com.project.usermanagement.shared.Constants;
import com.project.usermanagement.user.application.interfaces.IUserDeleteUseCase;
import com.project.usermanagement.user.domain.models.User;
import com.project.usermanagement.user.domain.repositories.IUserRepository;

@Service
public class UserDeleteUseCase implements IUserDeleteUseCase {

    @Autowired
    private IUserRepository userRepository;

    /**
     * Delete a user
     *
     * @param id Unique identifier in UUID
     * @return User create object.
     * @throws EntityNotExistsException if the user not exists
     */
    @Override
    public void execute(UUID id) {
        User user = userRepository.getById(id);
        if(user == null){
            throw new EntityNotExistsException(Constants.userNotFound);
        }
        userRepository.delete(id);
    }
    
}
