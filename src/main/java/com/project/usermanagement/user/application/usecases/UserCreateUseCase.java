package com.project.usermanagement.user.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.usermanagement.user.application.interfaces.IUserCreateUseCase;
import com.project.usermanagement.user.domain.models.User;
import com.project.usermanagement.user.domain.repositories.IUserRepository;

@Service
public class UserCreateUseCase implements IUserCreateUseCase {

    @Autowired
    private IUserRepository userRepository;

    /**
     * Create a user model in database
     *
     * @param user model
     * @return Create object user.
     * @throws EntityExistsException if the user exists
     */
    @Override
    public User execute(User user) {
        return userRepository.create(user);
    }
    
}
