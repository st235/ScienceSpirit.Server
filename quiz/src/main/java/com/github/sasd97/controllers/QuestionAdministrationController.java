package com.github.sasd97.controllers;

import com.github.sasd97.errors.IllegalArgumentError;
import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.QuestionModel;
import com.github.sasd97.models.ThemeModel;
import com.github.sasd97.models.VariantModel;
import com.github.sasd97.models.request.QuestionRequestModel;
import com.github.sasd97.repositories.QuestionRepository;
import com.github.sasd97.repositories.ThemesRepository;
import com.github.sasd97.repositories.VariantRepository;
import com.github.sasd97.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.github.sasd97.constants.MethodConstants.QuestionAdministration.CREATE;
import static com.github.sasd97.constants.MethodConstants.QuestionAdministration.INDEX;
import static com.github.sasd97.constants.ParamsConstants.ACCESS_TOKEN;

/**
 * Created by Alexadner Dadukin on 12/24/2016.
 */

@RestController
@RequestMapping(INDEX)
public class QuestionAdministrationController {

    private TokenUtils tokenUtils;
    private ThemesRepository themesRepository;
    private VariantRepository variantRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionAdministrationController(@NotNull TokenUtils tokenUtils,
                                            @NotNull ThemesRepository themesRepository,
                                            @NotNull VariantRepository variantRepository,
                                            @NotNull QuestionRepository questionRepository) {
        this.tokenUtils = tokenUtils;
        this.themesRepository = themesRepository;
        this.variantRepository = variantRepository;
        this.questionRepository = questionRepository;
    }

    @RequestMapping(value = CREATE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST)
    public QuestionModel createQuestion(@RequestParam(ACCESS_TOKEN) String token,
                                        @ModelAttribute QuestionRequestModel question) {
        if (!tokenUtils.isAdminToken(token)) throw new NotAuthorizedError();
        question.validate();

        ThemeModel theme = themesRepository.findOne(question.getThemeId());
        if (theme == null) throw new IllegalArgumentError("theme");

        VariantModel rightVariant = variantRepository.save(new VariantModel(question.getRightVariant()));
        List<VariantModel> othersVariants = new ArrayList<>();

        for (String variant: question.getOtherVariants()) {
            othersVariants.add(variantRepository.save(new VariantModel(variant)));
        }

        QuestionModel questionModel = new QuestionModel();
        questionModel.setTheme(theme);
        questionModel.setDescription(question.getDescription());
        questionModel.setRightVariant(rightVariant);
        questionModel.setOtherVariants(othersVariants);
        return questionRepository.save(questionModel);
    }
}
