package com.github.sasd97.models;

import com.github.sasd97.utils.DateUtils;

import javax.persistence.*;

/**
 * Created by Alexadner Dadukin on 1/2/2017.
 */

@Entity
@Table(name = "news")
public class NewsModel {

    @Id
    @GeneratedValue
    private Long newsId;

    private String title;
    private String description;

    @ManyToOne
    private UserModel author;

    @Column(nullable = false)
    private long creationDate = DateUtils.timestamp();

    public Long getNewsId() {
        return newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", creationDate=" + creationDate +
                '}';
    }
}
