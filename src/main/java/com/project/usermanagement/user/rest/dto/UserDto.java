package com.project.usermanagement.user.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @Schema(description = "Full user name")
    @NotNull
    private String userName;
    @Schema(description = "Main user email")
    @NotNull
    @Email
    private String email;
    @Schema(description = "User is active")
    @NotNull
    private boolean active;
}
