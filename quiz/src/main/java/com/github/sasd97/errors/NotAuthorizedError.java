package com.github.sasd97.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alexander on 20.12.16.
 */

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "FORBIDDEN")
public class NotAuthorizedError extends BasicError {

    @Override
    public int getCode() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "FORBIDDEN";
    }
}
