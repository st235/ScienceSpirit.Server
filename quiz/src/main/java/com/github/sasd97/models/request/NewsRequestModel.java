package com.github.sasd97.models.request;

import com.github.sasd97.errors.IllegalArgumentError;
import com.github.sasd97.services.ValidationService;

/**
 * Created by Alexadner Dadukin on 1/2/2017.
 */
public class NewsRequestModel extends ValidationService {

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
    public void validate() {
        if (title == null) throw new IllegalArgumentError("title");
        if (description == null) throw new IllegalArgumentError("description");
    }

    @Override
    public String toString() {
        return "NewsRequestModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
