package com.github.sasd97.models.request;

import java.util.List;

/**
 * Created by Alexadner Dadukin on 12/24/2016.
 */

public final class QuestionRequestModel extends RequestValidator {

    Long themeId;
    String description;
    String rightVariant;
    List<String> otherVariants;

    public Long getThemeId() {
        return themeId;
    }

    public String getDescription() { return description; }

    public String getRightVariant() {
        return rightVariant;
    }

    public List<String> getOtherVariants() {
        return otherVariants;
    }

    @Override
    protected boolean isValid() {
        return rightVariant != null & otherVariants != null && otherVariants.size() > 0;
    }

    @Override
    public String toString() {
        return "QuestionRequestModel{" +
                "themeId=" + themeId +
                "description=" + description +
                ", rightVariant='" + rightVariant + '\'' +
                ", otherVariants=" + otherVariants +
                '}';
    }
}
