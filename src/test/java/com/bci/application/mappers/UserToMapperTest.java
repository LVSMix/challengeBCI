package com.bci.application.mappers;

import com.bci.application.port.PhoneRequest;
import com.bci.application.port.UserRequest;
import com.bci.application.port.UserResponse;
import com.bci.domain.model.Phone;
import com.bci.domain.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserToMapperTest {

    private final UserToMapper userToMapper = new UserToMapper();

    @Test
    void testToUser() {
        // Datos de entrada
        UserRequest userRequest = new UserRequest(
                "John Doe",
                "johndoe@example.com",
                "Password1",
                List.of(new PhoneRequest("123456789", "1", "57"))
        );
        String token = "mockToken";
        boolean isActive = true;

        // Llamar al método
        User user = userToMapper.toUser(userRequest, token);

        // Verificar resultados
        assertEquals(userRequest.getName(), user.getName());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getPassword(), user.getPassword());
        assertEquals(token, user.getToken());
        assertEquals(isActive, user.isActive());
        assertEquals(1, user.getPhones().size());
        assertEquals("123456789", user.getPhones().get(0).getNumber());
    }

    @Test
    void testToResponse() {
        // Datos de entrada
        User user = new User(
                UUID.randomUUID(),
                "John Doe",
                "johndoe@example.com",
                "Password1",
                List.of(new Phone("123456789", "1", "57")),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "mockToken",
                true
        );

        // Llamar al método
        UserResponse userResponse = userToMapper.toResponse(user);

        // Verificar resultados
        assertEquals(user.getId(), userResponse.getId());
        assertEquals(user.getCreated(), userResponse.getCreated());
        assertEquals(user.getModified(), userResponse.getModified());
        assertEquals(user.getLastLogin(), userResponse.getLastLogin());
        assertEquals(user.getToken(), userResponse.getToken());
        assertEquals(user.isActive(), userResponse.isActive());
    }
}
