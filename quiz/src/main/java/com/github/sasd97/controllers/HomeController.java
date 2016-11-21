package com.github.sasd97.controllers;

import com.github.sasd97.models.UserModel;
import com.github.sasd97.repositories.UserCrudRepository;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import static com.github.sasd97.constants.MethodConstants.Home.*;

/**
 * Created by Alexadner Dadukin on 11/20/2016.
 */

@RestController
@RequestMapping(INDEX)
public class HomeController {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @RequestMapping(value = ADD,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.GET)
    public UserModel Add() {
        UserModel userModel = new UserModel();
        userModel.setFirstName("Alexander");
        userCrudRepository.save(userModel);
        return userModel;
    }

    @RequestMapping(value = GET,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.GET)
    public DeferredResult<UserModel> Get(@PathVariable("token") String token) {
        DeferredResult<UserModel> asyncTask = new DeferredResult<>();

        Unirest.get(String.format("https://graph.facebook.com/me?access_token=%s&fields=email", token)).asJsonAsync(new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                System.out.println(new Gson().toJson(response.getBody().getObject()));
                asyncTask.setResult(new UserModel());
            }

            @Override
            public void failed(UnirestException e) {
                asyncTask.setResult(new UserModel());
            }

            @Override
            public void cancelled() {
                asyncTask.setResult(new UserModel());
            }
        });

        return asyncTask;
    }
}
