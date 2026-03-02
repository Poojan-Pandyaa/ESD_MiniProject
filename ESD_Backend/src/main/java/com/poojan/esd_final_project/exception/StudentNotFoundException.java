package com.poojan.esd_final_project.exception;

public class StudentNotFoundException extends RuntimeException {
    private final String message;

    public StudentNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
