package com.bci.infraestructure.repository;

import com.bci.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        // Crear un usuario de prueba
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("Password1");

        // Guardar el usuario en la base de datos
        userRepository.save(user);

        // Buscar el usuario por email
        Optional<User> foundUser = userRepository.findByEmail("johndoe@example.com");

        // Verificar que el usuario fue encontrado
        assertTrue(foundUser.isPresent());
        assertEquals("John Doe", foundUser.get().getName());
        assertEquals("johndoe@example.com", foundUser.get().getEmail());
    }
}