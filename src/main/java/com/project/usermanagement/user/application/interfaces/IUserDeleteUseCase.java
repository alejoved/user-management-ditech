package com.project.usermanagement.user.application.interfaces;

import java.util.UUID;

public interface IUserDeleteUseCase {
    public void execute(UUID id);
}
