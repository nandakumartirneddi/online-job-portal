package com.portal.controller;

import com.portal.entity.userentity;
import com.portal.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private RepositoryUser userRepository;

    // 🔹 Get Logged-In User Details
    @GetMapping("/me")
    public ResponseEntity<userentity> getCurrentUser(@AuthenticationPrincipal User loggedInUser) {
        // ✅ Fetch user details using email from JWT token
        Optional<userentity> user = userRepository.findByEmail(loggedInUser.getUsername());

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }
    @GetMapping("/profile")
    public ResponseEntity<userentity> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        userentity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

                return ResponseEntity.ok(user);
    }
    
}