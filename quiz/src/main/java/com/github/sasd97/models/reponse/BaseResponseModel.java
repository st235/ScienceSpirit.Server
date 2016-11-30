package com.github.sasd97.models.reponse;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */
public class BaseResponseModel<T> {

    @Expose
    private boolean success;

    @Expose
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
