package com.github.sasd97.events;

import com.github.sasd97.errors.BasicError;

/**
 * Created by alexander on 18.12.16.
 */
public interface ParserResultListener<T> {

    void onSuccess(T result);
    default void onError(BasicError error) {
        System.out.print(error.toString());
    }
}
