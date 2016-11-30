package com.github.sasd97.models.reponse;

import com.github.sasd97.models.ErrorModel;
import com.google.gson.annotations.Expose;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */
public class ErrorResponseModel {

    @Expose
    private boolean success = false;

    @Expose
    private ErrorModel error;

    public ErrorResponseModel() {
        this.error = new ErrorModel();
    }

    public ErrorResponseModel(int code) {
        this.error = new ErrorModel(code);
    }

    public ErrorResponseModel(int code, String description) {
        this.error = new ErrorModel(code, description);
    }

    public ErrorResponseModel(int code, String description, String url) {
        this.error = new ErrorModel(code, description, url);
    }
}
