package com.german.proyecto.backend.models.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean enabled = true;
    private Instant creationDateTime = Instant.now();
    private String name;
    private String type;

    // Defino la clave foránea con @ManyToOne + @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private ProductEntity product;

    public FileEntity(String name, String type, ProductEntity product) {
        this.name = name;
        this.type = type;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
