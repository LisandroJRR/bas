package com.upiiz.bas.controllers;

import com.upiiz.bas.dtos.UserResponse;
import com.upiiz.bas.entities.User;
import com.upiiz.bas.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> authenticatedUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(UserResponse.from(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.allUsers()
                .stream()
                .map(UserResponse::from)
                .toList();
        return ResponseEntity.ok(users);
    }
}
