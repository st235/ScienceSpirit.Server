package com.github.sasd97.services;

import com.github.sasd97.errors.NotFoundError;
import com.github.sasd97.models.reponse.BaseResponseModel;
import com.github.sasd97.models.reponse.ErrorResponseModel;
import com.github.sasd97.repositories.AuthorizationRepository;
import com.github.sasd97.repositories.UserRepository;
import com.github.sasd97.utils.RequestUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.constraints.NotNull;

/**
 * Created by alexander on 18.12.16.
 */
public class WebCodeRequestService implements Callback<JsonNode> {

    private UserRepository userRepository;
    private AuthorizationRepository authorizationRepository;

    private DeferredResult<BaseResponseModel<?>> result;
    private static WebCodeRequestService webCodeRequestParser;

    private WebCodeRequestService() {}

    public static WebCodeRequestService getInstance(@NotNull DeferredResult<BaseResponseModel<?>> result,
                                                   @NotNull UserRepository userRepository,
                                                   @NotNull AuthorizationRepository authorizationRepository) {
        if (webCodeRequestParser == null) {
            webCodeRequestParser = new WebCodeRequestService();
        }

        webCodeRequestParser.setAsyncHandler(result);
        webCodeRequestParser.setRepositories(userRepository, authorizationRepository);

        return webCodeRequestParser;
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
                    .getInstance(result, userRepository, authorizationRepository)
                    .execute(token);
        } catch (JSONException e) {
            e.printStackTrace();
            result.setErrorResult(new NotFoundError());
        }
    }

    @Override
    public void failed(UnirestException e) {
        result.setErrorResult(
                new ErrorResponseModel(401, "Not allowed")
        );
    }

    @Override
    public void cancelled() {
        result.setErrorResult(
                new ErrorResponseModel(401, "Not allowed")
        );
    }

    private void setAsyncHandler(@NotNull DeferredResult<BaseResponseModel<?>> result) {
        this.result = result;
    }

    private void setRepositories(UserRepository userRepository,
                                 AuthorizationRepository authorizationRepository) {
        this.userRepository = userRepository;
        this.authorizationRepository = authorizationRepository;
    }
}
