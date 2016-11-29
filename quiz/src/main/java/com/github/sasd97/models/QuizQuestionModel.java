package com.github.sasd97.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
public class QuestionModel
{
    @Expose
    private String description;

    @Expose
    public void setDescription(int description) {
        this.type = description;
    }

    @Expose
    public String getDescription()
    {
        return description;
    }


    @Expose
    private AnswerModel RightAnswer;

    @Expose
    public void setRightAnswer(AnswerModel RightAnswer) {
        this.RightAnswer = RightAnswer;
    }

    @Expose
    public AnswerModel getRightAnswer()
    {
        return RightAnswer;
    }

    @Expose
    private AnswerModel[] Variants = new AnswerModel[4];

    public void setVariants(AnswerModel Variants[]) {
        this.Variants = Variants;
    }

    public AnswerModel[] getVariants()
    {
        return Variants;
    }



    private int TopicId;

    public void setTopicId(int TopicId) {
        this.TopicId = TopicId;
    }

    public int getTopicId()
    {
        return TopicId;
    }


    @Override
    public String toString() {
        return "QuestionModel{" +
                ", TopicId='" + TopicId + '\'' +
                ", description='" + description + '\''  +
                ", variants='" + Variants[0] + '\''  +
                '\''  + Variants[1]+
                '\''  + Variants[2]+
                '\''  + Variants[3]+
                ", RightAnswer='" + RightAnswer + '\'' +
                '}';
    }
}
