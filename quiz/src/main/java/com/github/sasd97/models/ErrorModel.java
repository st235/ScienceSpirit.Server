package com.github.sasd97.models;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */
public class ErrorModel {

    private int code;

    private String description;

    private String url;

    public ErrorModel() {
        this.code = 500;
    }

    public ErrorModel(int code) {
        this.code = code;
    }

    public ErrorModel(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public ErrorModel(int code, String description, String url) {
        this.code = code;
        this.description = description;
        this.url = url;
    }
}
