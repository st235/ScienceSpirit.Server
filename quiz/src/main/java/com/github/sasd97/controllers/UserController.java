package com.github.sasd97.controllers;

import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.UserModel;
import com.github.sasd97.models.request.UserRequestModel;
import com.github.sasd97.models.response.BaseResponseModel;
import com.github.sasd97.models.response.SimpleUserResponseModel;
import com.github.sasd97.repositories.UserRepository;
import com.github.sasd97.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.github.sasd97.constants.MethodConstants.User.*;
import static com.github.sasd97.constants.ParamsConstants.*;

/**
 * Created by Alexadner Dadukin on 12/29/2016.
 */

@RestController
@RequestMapping(INDEX)
public class UserController {

    private TokenUtils tokenUtils;
    private UserRepository userRepository;

    @Autowired
    public UserController(@NotNull TokenUtils tokenUtils,
                          @NotNull UserRepository userRepository) {
        this.tokenUtils = tokenUtils;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = GET_BY_ID,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<UserModel> getUserById(@PathVariable(USER_ID) Long userId,
                                                    @RequestParam(ACCESS_TOKEN) String accessToken) {
        if (!tokenUtils.withToken(accessToken, userId)) throw new NotAuthorizedError();
        return new BaseResponseModel<>(userRepository.findOne(userId)).success();
    }

    @RequestMapping(value = FIND_BY_NICKNAME,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<SimpleUserResponseModel> findByNickName(@PathVariable(USER_NICKNAME) String nickname,
            @RequestParam(ACCESS_TOKEN) String accessToken) {
        if (!tokenUtils.isToken(accessToken)) throw new NotAuthorizedError();
        return SimpleUserResponseModel.getResponse(userRepository.findByNickname(nickname));
    }

    @RequestMapping(value = EDIT,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST)
    public BaseResponseModel<UserModel> editUser(@PathVariable(USER_ID) Long userId,
                                                 @RequestParam(ACCESS_TOKEN) String accessToken,
                                                 @RequestBody UserRequestModel userRequestModel) {
        if (!tokenUtils.withToken(accessToken, userId)) throw new NotAuthorizedError();
        userRepository.editUser(userId, userRequestModel.getNickname(), userRequestModel.getAvatarUrl());
        return new BaseResponseModel<>(userRepository.findOne(userId)).success();
    }

    @RequestMapping(value = IS_NICKNAME_UNIQUE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<Boolean> isNicknameUnique(@PathVariable(USER_NICKNAME) String nickname,
                                                       @RequestParam(ACCESS_TOKEN) String accessToken) {
        if (!tokenUtils.isToken(accessToken)) throw new NotAuthorizedError();
        return new BaseResponseModel<>(userRepository.findByNickname(nickname) == null).success();
    }

}
