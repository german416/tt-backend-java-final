package com.german.proyecto.backend.models.dtos.responses;

import com.german.proyecto.backend.models.entities.ProductEntity;

public class PostProductResponse {
    public Integer id;
    public String name;
    public Double price = 0.0;
    public Integer stock = 0;
    public String description;
    public String[] files;

    public PostProductResponse(ProductEntity product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        stock = product.getStock();
        description = product.getDescription();
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

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }
}
