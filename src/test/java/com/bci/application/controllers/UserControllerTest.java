package com.bci.application.controllers;

import com.bci.application.port.UserRequest;
import com.bci.application.port.UserResponse;
import com.bci.application.services.UserService;
import com.bci.infraestructure.exceptions.EmailAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testRegisterUser_Success() {
        // Mock de la respuesta del servicio
        UserResponse mockResponse = new UserResponse(
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "mockToken",
                true
        );

        // Configurar el mock del servicio
        Mockito.when(userService.registerUser(any(UserRequest.class))).thenReturn(mockResponse);

        // Crear una solicitud de ejemplo
        UserRequest userRequest = new UserRequest(
                "John Doe",
                "johndoe@example.com",
                "Password1",
                null
        );

        // Llamar al método del controlador
        ResponseEntity<?> response = userController.registerUser(userRequest);

        // Verificar la respuesta
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        // Configurar el mock para lanzar una excepción
        Mockito.when(userService.registerUser(any(UserRequest.class)))
                .thenThrow(new EmailAlreadyExistsException("johndoe@example.com"));

        // Crear una solicitud de ejemplo
        UserRequest userRequest = new UserRequest(
                "John Doe",
                "johndoe@example.com",
                "Password1",
                null
        );

        // Llamar al método del controlador
        ResponseEntity<?> response = userController.registerUser(userRequest);

        // Verificar la respuesta
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("El correo ya registrado", ((java.util.Map<?, ?>) response.getBody()).get("mensaje"));
    }
}