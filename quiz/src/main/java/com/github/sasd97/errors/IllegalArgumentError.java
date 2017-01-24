package com.github.sasd97.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alexander on 18.12.16.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Arguments don`t fit all requirements")
public class IllegalArgumentError extends BasicError {

    private final String fieldName;

    public IllegalArgumentError(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public int getCode() {
        return 400;
    }

    @Override
    public String getDescription() {
        return String.format("Argument %1$s don`t fit all requirements", fieldName);
    }
}
