package com.github.sasd97.controllers;

import com.github.sasd97.models.response.BaseResponseModel;
import com.github.sasd97.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.github.sasd97.constants.MethodConstants.Token.*;
import static com.github.sasd97.constants.ParamsConstants.ACCESS_TOKEN;

/**
 * Created by Alexadner Dadukin on 1/2/2017.
 */

@RestController
@RequestMapping(INDEX)
public class TokenController {

    private TokenUtils tokenUtils;

    @Autowired
    public TokenController(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @RequestMapping(value = IS_ADMIN,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<Boolean> isAdmin(@PathVariable(ACCESS_TOKEN) String token) {
        return new BaseResponseModel<>(tokenUtils.isAdminToken(token)).success();
    }

    @RequestMapping(value = IS_AUTHORIZED,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<Boolean> isAuthorized(@PathVariable(ACCESS_TOKEN) String token) {
        return new BaseResponseModel<>(tokenUtils.isToken(token)).success();
    }

}
