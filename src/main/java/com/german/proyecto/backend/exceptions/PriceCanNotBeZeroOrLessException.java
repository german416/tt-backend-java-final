package com.german.proyecto.backend.exceptions;

public class PriceCanNotBeZeroOrLessException extends Exception {
    public PriceCanNotBeZeroOrLessException() {
        super("El precio no puede ser menor o igual a cero (0).");
    }
    public PriceCanNotBeZeroOrLessException(String message) {
        super(message);
    }
}
