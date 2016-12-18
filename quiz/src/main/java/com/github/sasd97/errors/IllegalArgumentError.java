package com.github.sasd97.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alexander on 18.12.16.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Arguments dont fit all requirements")
public class IllegalArgumentError extends BasicError {

    @Override
    public int getCode() {
        return 400;
    }

    @Override
    public String getDescription() {
        return "Arguments dont fit all requirements";
    }
}
