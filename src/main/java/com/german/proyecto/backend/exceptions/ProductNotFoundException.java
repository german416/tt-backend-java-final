package com.german.proyecto.backend.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("El producto no fue encontrado.");
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
