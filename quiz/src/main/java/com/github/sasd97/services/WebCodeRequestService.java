package com.github.sasd97.services;

import com.github.sasd97.errors.ActionCanceledError;
import com.github.sasd97.errors.NotFoundError;
import com.github.sasd97.events.ParserResultListener;
import com.github.sasd97.models.UserModel;
import com.github.sasd97.repositories.AuthorizationRepository;
import com.github.sasd97.repositories.UserRepository;
import com.github.sasd97.utils.RequestUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

import javax.validation.constraints.NotNull;

/**
 * Created by alexander on 18.12.16.
 */

public class WebCodeRequestService implements Callback<JsonNode> {

    private static WebCodeRequestService webCodeRequestParser;

    private UserRepository userRepository;
    private AuthorizationRepository authorizationRepository;

    private ParserResultListener<UserModel> listener;

    private WebCodeRequestService() {}

    public static WebCodeRequestService getInstance(@NotNull ParserResultListener<UserModel> listener,
                                                   @NotNull UserRepository userRepository,
                                                   @NotNull AuthorizationRepository authorizationRepository) {
        if (webCodeRequestParser == null) {
            webCodeRequestParser = new WebCodeRequestService();
        }

        webCodeRequestParser.addListener(listener);
        webCodeRequestParser.setRepositories(userRepository, authorizationRepository);
        return webCodeRequestParser;
    }

    private void addListener(@NotNull ParserResultListener<UserModel> listener) {
        this.listener = listener;
    }


    public void code(@NotNull String code,
                     @NotNull String redirect) {
        RequestUtils.getFacebookToken(code, redirect, this);
    }

    @Override
    public void completed(HttpResponse<JsonNode> response) {
        try {
            System.out.println(response.getBody().toString());
            String token = response.getBody().getObject().getString("access_token");

            FacebookParseService
                    .getInstance(listener, userRepository, authorizationRepository)
                    .execute(token);

        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(new NotFoundError());
        }
    }

    @Override
    public void failed(UnirestException e) {
        listener.onError(new ActionCanceledError());
    }

    @Override
    public void cancelled() {
        listener.onError(new ActionCanceledError());
    }

    private void setRepositories(UserRepository userRepository,
                                 AuthorizationRepository authorizationRepository) {
        this.userRepository = userRepository;
        this.authorizationRepository = authorizationRepository;
    }
}
