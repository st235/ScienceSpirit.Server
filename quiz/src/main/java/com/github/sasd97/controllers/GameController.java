package com.github.sasd97.controllers;

import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.repositories.QuestionRepository;
import com.github.sasd97.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.github.sasd97.constants.MethodConstants.Game.CREATE_GAME_BY_THEME;
import static com.github.sasd97.constants.MethodConstants.Game.INDEX;
import static com.github.sasd97.constants.ParamsConstants.ACCESS_TOKEN;

/**
 * Created by Alexadner Dadukin on 1/22/2017.
 */

@RequestMapping(INDEX)
public class GameController {

    private TokenUtils tokenUtils;
    private QuestionRepository questionRepository;

    @Autowired
    GameController(TokenUtils tokenUtils,
                   QuestionRepository questionRepository) {
        this.tokenUtils = tokenUtils;
        this.questionRepository = questionRepository;
    }

    @RequestMapping(value = CREATE_GAME_BY_THEME,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.GET)
    public String createGame(@PathVariable("themeId") Long themeId,
                             @RequestParam(ACCESS_TOKEN) String token) {
        if (!tokenUtils.isToken(token)) throw new NotAuthorizedError();
        return "";
    }
}
