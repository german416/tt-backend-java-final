package com.german.proyecto.backend.models.entities;

import com.german.proyecto.backend.models.requests.PostUserRequest;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean enabled = true;
    private Instant creationDateTime = Instant.now();
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String passwordHash;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    // @todo: insertar la lógica adecuada en los setters
    public UserEntity(PostUserRequest request) {
        setFirstName(request.firstName);
        setLastName(request.lastName);
        setEmail(request.email);
        setPasswordHash(request.password);
    }

    public UserEntity(String firstName, String lastName, String email, String passwordHash, RoleEntity role) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPasswordHash(passwordHash);
        setRole(role);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
