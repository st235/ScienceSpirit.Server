package com.github.sasd97.models;

import javax.persistence.*;

@Entity
@Table(name = "variants")
public class QuizVariantModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long variantId;

    private int type; //TODO: сделать enum`ом

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
