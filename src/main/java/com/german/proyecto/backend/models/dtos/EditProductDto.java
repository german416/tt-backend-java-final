package com.german.proyecto.backend.models.dtos;

import com.german.proyecto.backend.models.requests.PutProductRequest;

// todo: agregar validaciones
public class EditProductDto {
    public String name;
    public double price;
    public int stock;

    public EditProductDto(String name, double price, int stock) {
       this.name = name;
       this.price = price;
       this.stock = stock;
    }

    public EditProductDto(PutProductRequest request) {
        name = request.name;
        price = request.price;
        stock = request.stock;
    }
}
