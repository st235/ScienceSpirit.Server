package com.github.sasd97.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alexander on 18.12.16.
 */

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server try to cancel action")
public class ActionCanceledError extends BasicError {

    @Override
    public int getCode() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Server try to cancel action";
    }
}
