package com.portal.service;

import com.portal.entity.userentity;
import com.portal.repository.RepositoryUser;
import com.portal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 🔹 Handles user login
    public String login(String email, String password) {
        Optional<userentity> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            System.out.println("❌ Login failed: User not found.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");
        }

        System.out.println("✅ User found: " + email);
        System.out.println("🔍 Hashed password in DB: " + user.get().getPassword());
        System.out.println("🔍 Entered password: " + password);

        // ✅ Compare password using BCrypt
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            System.out.println("❌ Login failed: Invalid password.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials.");
        }

        System.out.println("✅ Password matched! Generating token...");
        return jwtUtil.generateToken(email); // ✅ Generate JWT token
    }

    // 🔹 Handles user registration (password hashing before saving)
    public userentity register(userentity user) {
        Optional<userentity> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            System.out.println("❌ User with email already exists: " + user.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists.");
        }

        // ✅ Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("✅ Registering new user: " + user.getEmail());
        return userRepository.save(user);
    }
}