package com.github.sasd97.errors;

import javax.management.RuntimeErrorException;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */
public abstract class BasicError extends RuntimeException {
    public abstract int getCode();
    public abstract String getDescription();
}
