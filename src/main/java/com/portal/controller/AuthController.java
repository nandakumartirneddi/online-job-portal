package com.portal.controller;

import com.portal.entity.userentity;
import com.portal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // 🔹 Register a new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody userentity user) {
        try {
            userentity registeredUser = authService.register(user);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully!");
            response.put("email", registeredUser.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 🔹 Login and return JWT token
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        try {
            // ✅ Validate input fields
            if (!credentials.containsKey("email") || !credentials.containsKey("password")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Email and password are required!"));
            }

            // ✅ Generate JWT token if credentials are correct
            String token = authService.login(credentials.get("email"), credentials.get("password"));

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }
}