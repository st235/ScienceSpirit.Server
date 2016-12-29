package com.github.sasd97.models.request;

/**
 * Created by Alexadner Dadukin on 12/29/2016.
 */
public final class UserRequestModel {

    private String nickname;
    private String avatarUrl;

    public String getNickname() {
        return nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
