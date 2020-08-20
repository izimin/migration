package ru.zimin.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ValidationException extends RuntimeException {

    private final Set errors = new LinkedHashSet<>();

    @SuppressWarnings("unchecked")
    public void put(String message) {
        errors.add(message);
    }

    @SuppressWarnings("unchecked")
    public void put(Object error) {
        errors.add(error);
    }

    public void isErrors() throws ValidationException {
        if (!errors.isEmpty()) {
            throw this;
        }
    }
    
    @SuppressWarnings("unchecked")
    public ValidationException(String message) {
        super();
        errors.add(message);
    }
}
