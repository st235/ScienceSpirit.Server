package com.github.sasd97.errors;

import javax.management.RuntimeErrorException;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */
public abstract class BasicError extends RuntimeException {
    public abstract int getCode();
    public abstract String getDescription();

    @Override
    public String toString() {
        return String.format("BasicError{\ncode=%1$d\ndescription=%2$s\n}",
                getCode(),
                getDescription());
    }
}
