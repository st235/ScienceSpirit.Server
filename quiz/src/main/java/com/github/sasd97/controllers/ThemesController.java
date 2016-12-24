package com.github.sasd97.controllers;

import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.ThemeModel;
import com.github.sasd97.models.response.BaseResponseModel;
import com.github.sasd97.repositories.ThemesRepository;
import com.github.sasd97.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.sasd97.constants.ParamsConstants.ACCESS_TOKEN;

import static com.github.sasd97.constants.MethodConstants.Themes.GET_ALL;
import static com.github.sasd97.constants.MethodConstants.Themes.INDEX;

/**
 * Created by Alexadner Dadukin on 12/23/2016.
 */

@RestController
@RequestMapping(INDEX)
public class ThemesController {

    private TokenUtils tokenUtils;
    private ThemesRepository themesRepository;

    @Autowired
    ThemesController(TokenUtils tokenUtils,
                     ThemesRepository themesRepository) {
        this.tokenUtils = tokenUtils;
        this.themesRepository = themesRepository;
    }

    @RequestMapping(value = GET_ALL,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<Iterable<ThemeModel>> getAll(@RequestParam(ACCESS_TOKEN) String token) {
        if (!tokenUtils.isToken(token)) throw new NotAuthorizedError();
        return new BaseResponseModel<>(themesRepository.findAll()).success();
    }
}
