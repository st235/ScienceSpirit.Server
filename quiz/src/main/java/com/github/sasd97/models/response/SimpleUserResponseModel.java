package com.github.sasd97.models.response;

import com.github.sasd97.errors.NotFoundError;
import com.github.sasd97.models.UserModel;

import javax.validation.constraints.NotNull;

/**
 * Created by Alexadner Dadukin on 12/29/2016.
 */
public final class SimpleUserResponseModel {

    private String nickname;
    private String avatarUrl;
    private Long registrationDate;

    SimpleUserResponseModel(@NotNull UserModel userModel) {
        nickname = userModel.getNickname();
        avatarUrl = userModel.getAvatarUrl();
        registrationDate = userModel.getRegistrationDate();
    }

    public static BaseResponseModel<SimpleUserResponseModel> getResponse(@NotNull UserModel userModel) {
        if (userModel == null) throw new NotFoundError();
        return new BaseResponseModel<>(new SimpleUserResponseModel(userModel)).success();
    }
}
