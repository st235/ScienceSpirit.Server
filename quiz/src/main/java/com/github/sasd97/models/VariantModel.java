package com.github.sasd97.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */

@Entity
@Table(name = "variants")
public class VariantModel {

    @Id
    @GeneratedValue
    private Long variantId;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "VariantModel{" +
                "variantId=" + variantId +
                ", title='" + title + '\'' +
                '}';
    }
}
