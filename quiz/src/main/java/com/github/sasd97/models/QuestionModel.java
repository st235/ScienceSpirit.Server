package com.github.sasd97.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */

@Entity
@Table(name = "questions")
public class QuestionModel {

    @Id
    @GeneratedValue
    private Long questionId;

    @ManyToOne
    private ThemeModel theme;

    private String description;

    @OneToOne
    private VariantModel media;

    @OneToOne
    private VariantModel rightVariant;

    @OneToMany
    private List<VariantModel> otherVariants;

    public ThemeModel getTheme() {
        return theme;
    }

    public void setTheme(ThemeModel theme) {
        this.theme = theme;
    }

    public VariantModel getRightVariant() {
        return rightVariant;
    }

    public void setRightVariant(VariantModel rightVariant) {
        this.rightVariant = rightVariant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VariantModel getMedia() {
        return media;
    }

    public void setMedia(VariantModel media) {
        this.media = media;
    }

    public List<VariantModel> getOtherVariants() {
        return otherVariants;
    }

    public void setOtherVariants(List<VariantModel> otherVariants) {
        this.otherVariants = otherVariants;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "questionId=" + questionId +
                ", theme=" + theme +
                ", description='" + description + '\'' +
                ", media=" + media +
                ", rightVariant=" + rightVariant +
                ", otherVariants=" + otherVariants +
                '}';
    }
}
