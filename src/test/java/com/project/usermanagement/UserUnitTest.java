package com.project.usermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.usermanagement.user.application.usecases.UserCreateUseCase;
import com.project.usermanagement.user.application.usecases.UserDeleteUseCase;
import com.project.usermanagement.user.application.usecases.UserGetUseCase;
import com.project.usermanagement.user.domain.models.User;
import com.project.usermanagement.user.infrastructure.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserUnitTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserGetUseCase userGetUseCase;

    @InjectMocks
    private UserCreateUseCase userCreateUseCase;

    @InjectMocks
    private UserDeleteUseCase userDeleteUseCase;

    @Test
    void testUnitGetUser() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserName("Test Alejandro");
        user.setEmail("alejandro@email.com");
        user.setActive(true);
        users.add(user);

        when(userRepository.get()).thenReturn(users);

        List<User> result = userGetUseCase.execute();
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testUnitGetByIdUser() {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setUserName("Test Alejandro");
        user.setEmail("alejandro@email.com");
        user.setActive(true);

        when(userRepository.getById(id)).thenReturn(user);

        User result = userGetUseCase.executeById(id);
        
        assertNotNull(result);
        assertEquals(result.getUserName(), user.getUserName());
        assertEquals(result.getEmail(), user.getEmail());
        assertEquals(result.isActive(), user.isActive());
    }

    @Test
    void testUnitCreateUser() {
        User inputUser = new User();
        inputUser.setUserName("Test Alejandro");
        inputUser.setEmail("alejandro@email.com");
        inputUser.setActive(true);

        User savedUser = new User();
        savedUser.setId(UUID.randomUUID());
        savedUser.setUserName("Test Alejandro");
        savedUser.setEmail("alejandro@email.com");
        savedUser.setActive(true);

        when(userRepository.create(inputUser)).thenReturn(savedUser);

        User result = userCreateUseCase.execute(inputUser);

        assertNotNull(result);
        assertEquals(result.getUserName(), savedUser.getUserName());
        assertEquals(result.getEmail(), savedUser.getEmail());
        assertEquals(result.isActive(), savedUser.isActive());
    }

    @Test
    void testUnitDeleteUser() {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setUserName("Test Alejandro");
        user.setEmail("alejandro@email.com");
        user.setActive(true);

        when(userRepository.getById(id)).thenReturn(user);
        doNothing().when(userRepository).delete(id);
        userDeleteUseCase.execute(id);
        verify(userRepository, times(1)).delete(id);
    }
}
