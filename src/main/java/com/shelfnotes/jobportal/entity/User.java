package com.shelfnotes.jobportal.entity;

import com.shelfnotes.jobportal.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Username cannot be empty")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Must contain a valid email")
    @NotBlank(message = "Email cannot be empty")
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}