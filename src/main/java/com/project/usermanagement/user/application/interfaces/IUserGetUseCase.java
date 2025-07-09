package com.project.usermanagement.user.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.project.usermanagement.user.domain.models.User;

public interface IUserGetUseCase {
    public List<User> execute();
    public User executeById(UUID id);
}
