package com.bci.application.services;

import com.bci.application.mappers.UserToMapper;
import com.bci.application.port.UserRequest;
import com.bci.application.port.UserResponse;
import com.bci.domain.model.User;
import com.bci.infraestructure.config.AppProperties;
import com.bci.infraestructure.config.JwtUtil;
import com.bci.infraestructure.exceptions.EmailAlreadyExistsException;
import com.bci.infraestructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AppProperties properties;
    private UserToMapper userToMapper;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, AppProperties properties, UserToMapper userToMapper) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.properties = properties;
        this.userToMapper = userToMapper;
    }

    public UserResponse registerUser(UserRequest request) {

        // Validación de email duplicado
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        // Validación de email (ya cubierta por @Email)
        if (!isValidEmail(request.getEmail())) {
            throw new IllegalArgumentException("Formato de correo no válido");
        }

        // Validación de contraseña con regex configurable
        if (!isValidPassword(request.getPassword())) {
            throw new IllegalArgumentException("La contraseña no cumple con el formato requerido");
        }
        // UUID + fecha + token
        // Datos para persistencia
        String token = jwtUtil.generateToken(request.getEmail()); // o UUID.randomUUID().toString()
        // Mapeo de teléfonos
        User user = userToMapper.toUser(request, token);
        // Guardado JPA + retorno UserResponse
        user = userRepository.save(user);
        return userToMapper.toResponse(user);
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean isValidPassword(String password) {
        return password.matches(properties.getPasswordRegex());
    }
}

