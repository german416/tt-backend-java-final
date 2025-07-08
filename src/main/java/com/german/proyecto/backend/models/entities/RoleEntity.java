package com.german.proyecto.backend.models.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    private Integer id;
    private Boolean enabled = true;
    private Instant creationDateTime = Instant.now();

    private String description;

    public RoleEntity() {}

    public RoleEntity(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}
