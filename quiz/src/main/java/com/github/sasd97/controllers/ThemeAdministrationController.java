package com.github.sasd97.controllers;

import com.github.sasd97.errors.IllegalArgumentError;
import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.ThemeModel;
import com.github.sasd97.repositories.ThemesRepository;
import com.github.sasd97.utils.AdminUtils;
import com.github.sasd97.utils.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.github.sasd97.constants.ParamsConstants.LANUGAGE;
import static com.github.sasd97.constants.ParamsConstants.ACCESS_TOKEN;

import static com.github.sasd97.constants.MethodConstants.ThemeAdministration.CREATE;
import static com.github.sasd97.constants.MethodConstants.ThemeAdministration.INDEX;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */

@RestController
@RequestMapping(INDEX)
public class ThemeAdministrationController {

    private AdminUtils adminUtils;
    private ThemesRepository themesRepository;

    @Autowired
    public ThemeAdministrationController(@NotNull AdminUtils adminUtils,
                                         @NotNull ThemesRepository themesRepository) {
        this.adminUtils = adminUtils;
        this.themesRepository = themesRepository;
    }

    @RequestMapping(value = CREATE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public ThemeModel createTheme(@RequestParam(LANUGAGE) String language,
                                  @RequestParam(ACCESS_TOKEN) String token,
                                  @RequestParam("title") String title) {

        System.out.println(language);
        System.out.println(token);
        System.out.println(title);

        if (!adminUtils.isAdminToken(token)) throw new NotAuthorizedError();

        LanguageUtils.Language ln = LanguageUtils.toLanguage(language);
        if (ln == LanguageUtils.Language.UNKNOWN) throw new IllegalArgumentError();

        ThemeModel theme = new ThemeModel();
        theme.setTitle(title);
        theme.setLocale(ln);

        return themesRepository.save(theme);
    }
}
