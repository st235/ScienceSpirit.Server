package com.github.sasd97.controllers;

import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.AuthorizationModel;
import com.github.sasd97.models.NewsModel;
import com.github.sasd97.models.request.NewsRequestModel;
import com.github.sasd97.models.response.BaseResponseModel;
import com.github.sasd97.repositories.NewsRepository;
import com.github.sasd97.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.sasd97.constants.MethodConstants.News.*;
import static com.github.sasd97.constants.ParamsConstants.*;

/**
 * Created by Alexadner Dadukin on 1/2/2017.
 */

@RestController
@RequestMapping(INDEX)
public class NewsController {

    private final TokenUtils tokenUtils;
    private final NewsRepository newsRepository;

    @Autowired
    public NewsController(TokenUtils tokenUtils,
                          NewsRepository newsRepository) {
        this.tokenUtils = tokenUtils;
        this.newsRepository = newsRepository;
    }

    @RequestMapping(value = CREATE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST)
    public BaseResponseModel<NewsModel> createNews(@RequestParam(ACCESS_TOKEN) String token,
                                               @RequestBody NewsRequestModel newsRequestModel) {
        if (!tokenUtils.isAdminToken(token)) throw new NotAuthorizedError();
        AuthorizationModel authorizationModel = tokenUtils.getToken(token);

        NewsModel newsModel = new NewsModel();
        newsModel.setTitle(newsRequestModel.getTitle());
        newsModel.setDescription(newsRequestModel.getDescription());
        newsModel.setAuthor(authorizationModel.getUser());

        return new BaseResponseModel<>(newsRepository.save(newsModel)).success();
    }

    @RequestMapping(value = GET_CONCRETE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<NewsModel> getConcreteNew(@PathVariable("newsId") Long newsId,
                                                       @RequestParam(ACCESS_TOKEN) String token) {
        if (!tokenUtils.isAdminToken(token)) throw new NotAuthorizedError();
        return new BaseResponseModel<>(newsRepository.findOne(newsId)).success();
    }

    @RequestMapping(value = GET_ALL,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    public BaseResponseModel<List<NewsModel>> getAll(@RequestParam(ACCESS_TOKEN) String token,
                                                     @RequestParam(value = PAGE, required = false, defaultValue = "0") int page,
                                                     @RequestParam(value = LIMIT, required = false, defaultValue = "20") int limit) {
        if (!tokenUtils.isAdminToken(token)) throw new NotAuthorizedError();
        return new BaseResponseModel<>(newsRepository.findAllWithPage(new PageRequest(page, limit))).success();
    }
}
