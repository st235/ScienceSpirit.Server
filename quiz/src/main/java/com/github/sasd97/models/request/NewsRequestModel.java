package com.github.sasd97.models.request;

/**
 * Created by Alexadner Dadukin on 1/2/2017.
 */
public class NewsRequestModel {

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NewsRequestModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
