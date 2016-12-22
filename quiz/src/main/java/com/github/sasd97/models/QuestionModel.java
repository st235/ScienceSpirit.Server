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
                ", rightVariant=" + rightVariant +
                ", otherVariants=" + otherVariants +
                '}';
    }
}
