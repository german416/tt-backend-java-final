package com.german.proyecto.backend.models.responses;

public class GenericErrorResponse {
    public String message;
    public String stackTrace;

    public GenericErrorResponse(String message, String stackTrace) {
        this.message = message;
        this.stackTrace = stackTrace;
    }
}
