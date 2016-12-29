package com.github.sasd97.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Alexadner Dadukin on 12/25/2016.
 */

@Entity
@Table(name = "media")
public class MediaModel {

    public enum Type {
        AUDIO,
        VIDEO,
        IMAGE
    }

    @Id
    @GeneratedValue
    private Long mediaId;

    private Type type;

    private String url;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MediaModel{" +
                "mediaId=" + mediaId +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
