package com.github.sasd97.controllers;

import com.github.sasd97.errors.IllegalArgumentError;
import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.ThemeModel;
import com.github.sasd97.models.response.BaseResponseModel;
import com.github.sasd97.repositories.ThemesRepository;
import com.github.sasd97.utils.LanguageUtils;
import com.github.sasd97.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.sasd97.constants.MethodConstants.Themes.GET_ALL;
import static com.github.sasd97.constants.MethodConstants.Themes.GET_BY_LANGUAGE;
import static com.github.sasd97.constants.MethodConstants.Themes.INDEX;
import static com.github.sasd97.constants.ParamsConstants.ACCESS_TOKEN;
import static com.github.sasd97.constants.ParamsConstants.LANGUAGE;

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

    @RequestMapping(value = GET_BY_LANGUAGE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<List<ThemeModel>> getByLanguage(@RequestParam(LANGUAGE) String language,
                                                             @RequestParam(ACCESS_TOKEN) String token) {
        if (!tokenUtils.isToken(token)) throw new NotAuthorizedError();
        LanguageUtils.Language ln = LanguageUtils.toLanguage(language);
        if (ln == LanguageUtils.Language.UNKNOWN) throw new IllegalArgumentError("language");
        return new BaseResponseModel<>(themesRepository.sortByLanguage(ln)).success();
    }
}
