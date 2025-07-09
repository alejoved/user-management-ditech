package com.project.usermanagement.user.domain.repositories;

import java.util.List;
import java.util.UUID;

import com.project.usermanagement.user.domain.models.User;

public interface IUserRepository {
    public List<User> get();
    public User getById(UUID id);
    public User create(User user);
    public void delete(UUID id);
}
