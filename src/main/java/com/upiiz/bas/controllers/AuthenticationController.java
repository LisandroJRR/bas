package com.upiiz.bas.controllers;

import com.upiiz.bas.dtos.LoginResponse;
import com.upiiz.bas.dtos.LoginUserDto;
import com.upiiz.bas.dtos.RegisterUserDto;
import com.upiiz.bas.dtos.UserResponse;
import com.upiiz.bas.entities.User;
import com.upiiz.bas.services.AuthenticationService;
import com.upiiz.bas.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserDto dto) {
        User createdUser = authenticationService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(createdUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserDto dto) {
        User authenticatedUser = authenticationService.authenticate(dto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse response = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(response);
    }
}
