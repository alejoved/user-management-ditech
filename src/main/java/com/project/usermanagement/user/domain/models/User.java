package com.project.usermanagement.user.domain.models;

import java.util.UUID;
import lombok.Data;

@Data
public class User {
    private UUID id;
    private String userName;
    private String email;
    private boolean active;
}
