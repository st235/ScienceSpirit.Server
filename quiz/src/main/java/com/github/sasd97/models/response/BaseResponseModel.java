package com.github.sasd97.models.response;

/**
 * Created by Alexander Dadukin on 11/30/2016.
 */

public class BaseResponseModel<T> {

    private boolean success;

    private T response;

    public BaseResponseModel(T response) {
        this.response = response;
    }

    public BaseResponseModel<T> success() {
        this.success = true;
        return this;
    }

    public BaseResponseModel<T> unsuccess() {
        this.success = false;
        return this;
    }
}
