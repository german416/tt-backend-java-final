package com.german.proyecto.backend.models.dtos;

import com.german.proyecto.backend.models.dtos.requests.PutProductRequest;

// todo: agregar validaciones
public class EditProductDto {
    public String name;
    public String description;
    public double price;
    public int stock;

    public EditProductDto(String name, String description, double price, int stock) {
       this.name = name;
       this.price = price;
       this.stock = stock;
       this.description = description;
    }

    public EditProductDto(PutProductRequest request) {
        name = request.name;
        price = request.price;
        stock = request.stock;
        description = request.description;

    }
}
