package com.student_api.studentapi.Exception;

public class ResourceNotFoundException extends RuntimeException {
//It is file which handles the Exception of the application
    public ResourceNotFoundException(String message) {
        super(message);
    }
}