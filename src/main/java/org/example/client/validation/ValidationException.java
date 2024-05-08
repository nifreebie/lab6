package org.example.client.validation;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<String> errors;

    public ValidationException(List<String> error) {
        this.errors = error;
    }
}
