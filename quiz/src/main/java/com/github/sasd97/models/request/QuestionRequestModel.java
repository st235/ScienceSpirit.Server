package com.github.sasd97.models.request;

import com.github.sasd97.errors.IllegalArgumentError;
import com.github.sasd97.services.ValidationService;

import java.util.List;

/**
 * Created by Alexadner Dadukin on 12/24/2016.
 */

public final class QuestionRequestModel extends ValidationService {

    Long themeId;
    String description;
    String rightVariant;
    List<String> otherVariants;

    public Long getThemeId() {
        return themeId;
    }

    public String getDescription() {
        return description;
    }

    public String getRightVariant() {
        return rightVariant;
    }

    public List<String> getOtherVariants() {
        return otherVariants;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRightVariant(String rightVariant) {
        this.rightVariant = rightVariant;
    }

    public void setOtherVariants(List<String> otherVariants) {
        this.otherVariants = otherVariants;
    }

    @Override
    public void validate() {
        if (themeId == null) throw new IllegalArgumentError("themeId");
        if (description == null) throw new IllegalArgumentError("description");
        if (rightVariant == null) throw new IllegalArgumentError("rightVariant");
        if (otherVariants == null || otherVariants.size() <= 0) throw new IllegalArgumentError("otherVariants");
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
