package com.github.sasd97.models;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuizQuestionModel
{
    //TODO: добавить id базы данных

    @Expose
    private int topicId;

    @Expose
    private String description;

    @Expose
    private QuizVariantModel rightAnswer;

    @Expose
    private List<QuizVariantModel> variants;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuizVariantModel getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(QuizVariantModel rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<QuizVariantModel> getVariants() {
        return variants;
    }

    public void setVariants(List<QuizVariantModel> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "QuizQuestionModel{" +
                "topicId=" + topicId +
                ", description='" + description + '\'' +
                ", rightAnswer=" + rightAnswer +
                ", variants=" + variants +
                '}';
    }
}
