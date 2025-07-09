package com.project.usermanagement.user.rest.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.usermanagement.user.application.interfaces.IUserCreateUseCase;
import com.project.usermanagement.user.application.interfaces.IUserDeleteUseCase;
import com.project.usermanagement.user.application.interfaces.IUserGetUseCase;
import com.project.usermanagement.user.domain.models.User;
import com.project.usermanagement.user.rest.dto.UserDto;
import com.project.usermanagement.user.rest.dto.UserResponseDto;
import com.project.usermanagement.user.rest.mapper.UserRestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users", description = "User related operations")
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserGetUseCase userGetUseCase;
    @Autowired
    private IUserCreateUseCase userCreateUseCase;
    @Autowired
    private IUserDeleteUseCase userDeleteUseCase;


    @Operation(summary = "Get all users currently")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all users successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userGetUseCase.execute();
        List<UserResponseDto> userResponseDtos = users.stream().map(UserRestMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(userResponseDtos);
    }

    @Operation(summary = "Get an user existing by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an user successfully"),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@Parameter(description = "uuid for filter user") @PathVariable UUID id) {
        User user = userGetUseCase.executeById(id);
        UserResponseDto userResponseDto = UserRestMapper.toDTO(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserDto userDto) {
        User user = UserRestMapper.toDomain(userDto);
        User response = userCreateUseCase.execute(user);
        UserResponseDto userResponseDto = UserRestMapper.toDTO(response);
        return ResponseEntity.ok(userResponseDto);
    }

    @Operation(summary = "Delete an user by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userDeleteUseCase.execute(id);
    }
}
