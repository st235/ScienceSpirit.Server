package com.github.sasd97.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alexander on 18.12.16.
 */

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unknown error")
public class UnhandledError extends BasicError {

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Unknown error";
    }
}
