package com.upiiz.bas.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterUserDto {

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String fullName;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 100, message = "El correo no puede superar 100 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 72, message = "La contraseña debe tener entre 8 y 72 caracteres")
    private String password;

    public String getFullName() { return fullName; }
    public RegisterUserDto setFullName(String fullName) { this.fullName = fullName; return this; }
    public String getEmail() { return email; }
    public RegisterUserDto setEmail(String email) { this.email = email; return this; }
    public String getPassword() { return password; }
    public RegisterUserDto setPassword(String password) { this.password = password; return this; }
}
