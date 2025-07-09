package com.project.usermanagement.user.application.interfaces;

import com.project.usermanagement.user.domain.models.User;

public interface IUserCreateUseCase {
    public User execute(User user);
}
