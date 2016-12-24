package com.github.sasd97.models.request;

import com.github.sasd97.errors.IllegalArgumentError;

/**
 * Created by Alexadner Dadukin on 12/24/2016.
 */
public abstract class RequestValidator {

    protected abstract boolean isValid();
    public void validate() {
        if (!isValid()) throw new IllegalArgumentError();
    }
}
