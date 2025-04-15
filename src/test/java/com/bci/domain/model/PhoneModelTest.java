package com.bci.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PhoneTest {

    @Test
    void testPhoneConstructorAndGetters() {
        // Crear un objeto Phone usando el constructor
        Phone phone = new Phone("123456789", "1", "57");

        // Verificar que los valores se asignaron correctamente
        assertEquals("123456789", phone.getNumber());
        assertEquals("1", phone.getCityCode());
        assertEquals("57", phone.getContryCode());
    }

    @Test
    void testPhoneSetters() {
        // Crear un objeto Phone vacío
        Phone phone = new Phone();

        // Establecer valores usando los setters
        phone.setNumber("987654321");
        phone.setCityCode("2");
        phone.setContryCode("58");

        // Verificar que los valores se asignaron correctamente
        assertEquals("987654321", phone.getNumber());
        assertEquals("2", phone.getCityCode());
        assertEquals("58", phone.getContryCode());
    }

    @Test
    void testPhoneAllArgsConstructor() {
        // Crear un objeto Phone usando el constructor con todos los argumentos
        Phone phone = new Phone(1L, "123456789", "1", "57");

        // Verificar que los valores se asignaron correctamente
        assertEquals(1L, phone.getId());
        assertEquals("123456789", phone.getNumber());
        assertEquals("1", phone.getCityCode());
        assertEquals("57", phone.getContryCode());
    }

    @Test
    void testPhoneNoArgsConstructor() {
        // Crear un objeto Phone usando el constructor sin argumentos
        Phone phone = new Phone();

        // Verificar que el objeto no sea nulo
        assertNotNull(phone);
    }
}