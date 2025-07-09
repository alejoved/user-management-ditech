package com.project.usermanagement.user.rest.dto;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserResponseDto {
    @Schema(description = "User ID")
    private UUID  id;
    @Schema(description = "Full user name")
    private String userName;
    @Schema(description = "Main user email")
    private String email;
    @Schema(description = "User is active")
    private boolean active;
}
