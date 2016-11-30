package com.github.sasd97.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotFoundError extends RuntimeException implements Error {

    @Override
    public int getCode() {
        return 404;
    }

    @Override
    public String getDescription() {
        return "Not found";
    }
}
