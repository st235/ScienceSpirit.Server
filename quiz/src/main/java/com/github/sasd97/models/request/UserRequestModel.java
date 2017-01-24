package com.github.sasd97.models.request;

import com.github.sasd97.errors.IllegalArgumentError;
import com.github.sasd97.services.ValidationService;

/**
 * Created by Alexadner Dadukin on 12/29/2016.
 */
public final class UserRequestModel extends ValidationService {

    private String nickname;
    private String avatarUrl;

    public String getNickname() {
        return nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public void validate() {
        if (nickname == null) throw new IllegalArgumentError("nickname");
        if (avatarUrl == null) throw new IllegalArgumentError("avatarUrl");
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
