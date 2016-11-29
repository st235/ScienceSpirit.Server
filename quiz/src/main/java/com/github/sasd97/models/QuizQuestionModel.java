package com.github.sasd97.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuizQuestionModel
{
    @Id
    @Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    @Expose
    private int topicId;

    @Expose
    private String description;

    @Expose
    @OneToOne
    private QuizVariantModel rightAnswer;

    @Expose
    @OneToMany
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
