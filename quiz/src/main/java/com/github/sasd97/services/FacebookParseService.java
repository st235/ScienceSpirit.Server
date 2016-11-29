package com.github.sasd97.services;

import com.github.sasd97.models.UserModel;
import com.github.sasd97.utils.RequestUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.constraints.NotNull;

/**
 * Created by Alexadner Dadukin on 11/29/2016.
 */
public class FacebookParseService implements Callback<JsonNode> {

    private DeferredResult<UserModel> result;
    private static FacebookParseService facebookParseService;

    private FacebookParseService() {}

    public static FacebookParseService getInstance(@NotNull DeferredResult<UserModel> result) {
        if (facebookParseService == null) {
            facebookParseService = new FacebookParseService();
        }

        facebookParseService.setAsyncHandler(result);
        return facebookParseService;
    }

    private void setAsyncHandler(@NotNull DeferredResult<UserModel> result) {
        this.result = result;
    }

    public void execute(String token) {
        RequestUtils.getFacebookUser(token, this);
    }

    @Override
    public void completed(HttpResponse<JsonNode> response) {
        UserModel user = new UserModel();

        try {
            user.setSocialId(response.getBody().getObject().getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
            result.setErrorResult("No id field");
        }

        result.setResult(user);
    }

    @Override
    public void failed(UnirestException e) {
        e.printStackTrace();
        result.setErrorResult("Error");
    }

    @Override
    public void cancelled() {
        result.setErrorResult("cancelled");
    }
}
