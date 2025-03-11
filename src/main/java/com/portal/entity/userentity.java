package com.portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class userentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 🔹 Auto-generates user ID
    private Long id;

    @Column(nullable = false, unique = true)  // 🔹 Ensures email is unique & required
    private String email;

    @Column(nullable = false)  // 🔹 Password is required
    private String password;

    @Column(nullable = false)  // 🔹 Full name is required
    private String fullName;

    @Enumerated(EnumType.STRING)  // 🔹 Stores role as "USER" or "ADMIN"
    private Roleenum role;
    
}
