package com.github.sasd97.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "answers")
public class AnswerModel
{
    @Expose
    private int type;

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
