package com.github.sasd97.models;

import com.github.sasd97.utils.LanguageUtils;

import javax.persistence.*;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */

@Entity
@Table(name = "themes")
public class ThemeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long themeId;

    @Column(nullable = false, unique = true)
    private String title;

    private LanguageUtils.Language locale;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LanguageUtils.Language getLocale() {
        return locale;
    }

    public void setLocale(LanguageUtils.Language locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "ThemeModel{" +
                "themeId=" + themeId +
                ", title='" + title + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }
}
