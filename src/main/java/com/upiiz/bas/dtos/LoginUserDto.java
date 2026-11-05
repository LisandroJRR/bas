package com.upiiz.bas.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginUserDto {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public String getEmail() { return email; }
    public LoginUserDto setEmail(String email) { this.email = email; return this; }
    public String getPassword() { return password; }
    public LoginUserDto setPassword(String password) { this.password = password; return this; }
}
