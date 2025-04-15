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
    @NotNull
    @NotBlank(message = "El nombre es obligatorio")
    public String name;
    @Email(message = "El correo no tiene un formato válido")
    @NotBlank(message = "El correo es obligatorio")
    public String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$", message = "La contraseña no cumple con el formato")
    public String password;
    @NotNull(message = "La lista de telefonos puede ser vacia pero no nula")
    public List<@Valid PhoneRequest> phones;
}
