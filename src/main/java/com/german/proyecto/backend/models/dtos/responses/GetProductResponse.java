package com.german.proyecto.backend.models.dtos.responses;

import com.german.proyecto.backend.models.entities.FileEntity;
import com.german.proyecto.backend.models.entities.ProductEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GetProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
    private String description;
    private Instant creationDateTime;
    private List<String> files;

    public GetProductResponse(ProductEntity product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.description = product.getDescription();
        this.creationDateTime = product.getCreationDateTime();

        List<FileEntity> files = product.getFiles();

        if(!files.isEmpty()) {
            this.files = new ArrayList<String>();
            for (FileEntity file : files) {
                this.files.add(file.toString());
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Instant creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
