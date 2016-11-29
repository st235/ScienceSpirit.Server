package com.github.sasd97.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "variants")
public class QuizVariantModel
{
    @Id
    @Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long variantId;

    @Expose
    private int type; //TODO: сделать enum`ом

    @Expose
    private String value;

    public int getType()
    {
        return type;
    }

    public String getValue()
    {
        return value;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnswerModel{" +
                ", type='" + type + '\'' +
                ", value='" + value + '\''  +
                '}';
    }
}
