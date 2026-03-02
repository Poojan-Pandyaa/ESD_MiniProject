package com.poojan.esd_final_project.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private final String message;

    public EmployeeNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
