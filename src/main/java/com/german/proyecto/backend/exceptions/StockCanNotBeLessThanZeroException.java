package com.german.proyecto.backend.exceptions;

public class StockCanNotBeLessThanZeroException extends Exception {
    public StockCanNotBeLessThanZeroException() {
        super("El stock no puede ser menor que cero (0).");
    }
    public StockCanNotBeLessThanZeroException(String message) {
        super(message);
    }
}
