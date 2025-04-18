package com.bci.application.port;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @NotNull(message = "El nombre no puede ser nulo")
    public String name;
    @Email(message = "El correo no tiene un formato válido")
    @NotBlank(message = "El correo es obligatorio")
    @NotNull(message = "El correo no puede ser nulo")
    public String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$", message = "La contraseña no cumple con el formato")
    @NotNull(message = "La password no puede ser nula")
    public String password;
    @NotNull(message = "La lista de telefonos puede ser vacia pero no nula")
    public List<@Valid PhoneRequest> phones;
}
