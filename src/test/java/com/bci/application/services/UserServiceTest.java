package com.bci.application.services;

import com.bci.application.mappers.UserToMapper;
import com.bci.application.port.UserRequest;
import com.bci.application.port.UserResponse;
import com.bci.domain.model.User;
import com.bci.infraestructure.config.AppProperties;
import com.bci.infraestructure.config.JwtUtil;
import com.bci.infraestructure.exceptions.EmailAlreadyExistsException;
import com.bci.infraestructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AppProperties properties;

    @Mock
    private UserToMapper userToMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser_Success() {
        // Mock de propiedades
        when(properties.getPasswordRegex()).thenReturn("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
        when(jwtUtil.generateToken(any())).thenReturn("mockToken");

        // Mock de repositorio y mapper
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        User mockUser = new User();
        when(userToMapper.toUser(any(), any())).thenReturn(mockUser);
        when(userRepository.save(any())).thenReturn(mockUser);
        UserResponse mockResponse = new UserResponse();
        when(userToMapper.toResponse(any())).thenReturn(mockResponse);

        // Crear solicitud
        UserRequest request = new UserRequest("John Doe", "johndoe@example.com", "Password1", null);

        // Ejecutar método
        UserResponse response = userService.registerUser(request);

        // Verificar resultados
        assertNotNull(response);
        assertEquals(mockResponse, response);
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        // Mock de repositorio
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(new User()));

        // Crear solicitud
        UserRequest request = new UserRequest("John Doe", "johndoe@example.com", "Password1", null);

        // Verificar excepción
        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(request));
    }

    @Test
    void testRegisterUser_InvalidPassword() {
        // Mock de propiedades
        when(properties.getPasswordRegex()).thenReturn("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");

        // Crear solicitud con contraseña inválida
        UserRequest request = new UserRequest("John Doe", "johndoe@example.com", "password", null);

        // Verificar excepción
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(request));
    }
}