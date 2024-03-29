package com.github.sasd97.controllers;

import com.github.sasd97.errors.IllegalArgumentError;
import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.ThemeModel;
import com.github.sasd97.repositories.ThemesRepository;
import com.github.sasd97.utils.TokenUtils;
import com.github.sasd97.utils.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.github.sasd97.constants.ParamsConstants.LANGUAGE;
import static com.github.sasd97.constants.ParamsConstants.ACCESS_TOKEN;

import static com.github.sasd97.constants.MethodConstants.ThemesAdministration.CREATE;
import static com.github.sasd97.constants.MethodConstants.ThemesAdministration.INDEX;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */

@RestController
@RequestMapping(INDEX)
public class ThemesAdministrationController {

    private TokenUtils tokenUtils;
    private ThemesRepository themesRepository;

    @Autowired
    public ThemesAdministrationController(@NotNull TokenUtils tokenUtils,
                                          @NotNull ThemesRepository themesRepository) {
        this.tokenUtils = tokenUtils;
        this.themesRepository = themesRepository;
    }

    @RequestMapping(value = CREATE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public ThemeModel createTheme(@RequestParam(LANGUAGE) String language,
                                  @RequestParam(ACCESS_TOKEN) String token,
                                  @RequestParam("title") String title) {
        if (!tokenUtils.isAdminToken(token)) throw new NotAuthorizedError();

        LanguageUtils.Language ln = LanguageUtils.toLanguage(language);
        if (ln == LanguageUtils.Language.UNKNOWN) throw new IllegalArgumentError("language");

        ThemeModel theme = new ThemeModel();
        theme.setTitle(title);
        theme.setLocale(ln);

        return themesRepository.save(theme);
    }
}
