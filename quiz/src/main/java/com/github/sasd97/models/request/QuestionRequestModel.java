package com.github.sasd97.models.request;

import java.util.List;

/**
 * Created by Alexadner Dadukin on 12/24/2016.
 */

public class QuestionRequestModel extends RequestValidator {

    Long themeId;
    String rightVariant;
    List<String> otherVariants;

    public Long getThemeId() {
        return themeId;
    }

    public String getRightVariant() {
        return rightVariant;
    }

    public List<String> getOtherVariants() {
        return otherVariants;
    }

    @Override
    protected boolean isValid() {
        return themeId != null && rightVariant != null & otherVariants != null && otherVariants.size() > 0;
    }

    @Override
    public String toString() {
        return "QuestionRequestModel{" +
                "themeId=" + themeId +
                ", rightVariant='" + rightVariant + '\'' +
                ", otherVariants=" + otherVariants +
                '}';
    }
}
