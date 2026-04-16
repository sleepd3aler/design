package ru.report_generation_app.exceptions;

public class GenerationException extends RuntimeException {
    public GenerationException(String message) {
        super(message);
    }
}
