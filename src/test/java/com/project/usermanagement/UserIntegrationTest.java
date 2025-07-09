package com.project.usermanagement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.usermanagement.user.infrastructure.entities.UserEntity;
import com.project.usermanagement.user.infrastructure.repositories.IUserJpaRepository;
import com.project.usermanagement.user.rest.dto.UserDto;
import com.project.usermanagement.user.rest.dto.UserResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserJpaRepository userRepository;
    
    private UserEntity userEntity;

    @BeforeEach
    void beforeTest() throws Exception {
        userEntity = new UserEntity();
        userEntity.setUserName("Test User Name");
        userEntity.setEmail("test@email.com");
        userEntity.setActive(true);
    }

    @AfterEach
    void afterEach(){
        userRepository.deleteAll();
    }

    @Test
    void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("Test User Name");
        userDto.setEmail("test@email.com");
        userDto.setActive(true);

        String responseJson = mockMvc.perform(post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userDto)))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        UserResponseDto userResponseDto = objectMapper.readValue(responseJson, UserResponseDto.class);
        assertNotNull(userResponseDto);
        assertEquals(userResponseDto.getUserName(), userDto.getUserName());
        assertEquals(userResponseDto.getEmail(), userDto.getEmail());
        assertEquals(userResponseDto.isActive(), userDto.isActive());
    }

    @Test
    void testGetUser() throws Exception {
        userRepository.save(this.userEntity);
        String responseJson = mockMvc.perform(get("/user")
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        List<UserResponseDto> userResponseDto = objectMapper.readValue(responseJson, new TypeReference<List<UserResponseDto>>(){});
        assertNotNull(userResponseDto);
        assertFalse(userResponseDto.isEmpty());
    }

    @Test
    void testGetByIdUser() throws Exception {
        UserEntity user = userRepository.save(this.userEntity);
        String responseJson = mockMvc.perform(get("/user/" + user.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        UserResponseDto userResponseDto = objectMapper.readValue(responseJson, UserResponseDto.class);
        assertNotNull(userResponseDto);
        assertEquals(userResponseDto.getUserName(), user.getUserName());
        assertEquals(userResponseDto.getEmail(), user.getEmail());
        assertEquals(userResponseDto.isActive(), user.isActive());
    }

    @Test
    void testGetByIdNotFoundUser() throws Exception {
        UUID id = UUID.randomUUID();
        String responseJson = mockMvc.perform(get("/user/" + id)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isNotFound())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();
        String expectedError = "User not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }

    @Test
    void testDeleteUser() throws Exception {
        UserEntity user = userRepository.save(this.userEntity);
        mockMvc.perform(delete("/user/"+ user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        assertTrue(userRepository.findById(user.getId()).isEmpty());
    }


    @Test
    void testNotFoundDeleteUser() throws Exception {
        UUID id = UUID.randomUUID();
        userRepository.save(this.userEntity);
        String responseJson = mockMvc.perform(delete("/user/"+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        String expectedError = "User not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }

}
