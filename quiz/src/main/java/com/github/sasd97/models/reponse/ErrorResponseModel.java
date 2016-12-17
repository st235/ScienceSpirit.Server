package com.github.sasd97.models.reponse;

import com.github.sasd97.models.ErrorModel;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */
public class ErrorResponseModel {

    private boolean success = false;

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
