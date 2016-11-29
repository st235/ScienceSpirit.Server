package com.github.sasd97.services;

import com.github.sasd97.models.UserModel;
import com.github.sasd97.repositories.UserCrudRepository;
import com.github.sasd97.utils.RequestUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Alexadner Dadukin on 11/29/2016.
 */

@Service
public class FacebookParseService implements Callback<JsonNode> {

    private UserCrudRepository userCrudRepository;

    private DeferredResult<UserModel> result;
    private static FacebookParseService facebookParseService;

    private FacebookParseService() {}

    public static FacebookParseService getInstance(@NotNull DeferredResult<UserModel> result,
                                                   @NotNull UserCrudRepository userCrudRepository) {
        if (facebookParseService == null) {
            facebookParseService = new FacebookParseService();
        }

        facebookParseService.setAsyncHandler(result);
        facebookParseService.setUserRepository(userCrudRepository);
        return facebookParseService;
    }

    private void setAsyncHandler(@NotNull DeferredResult<UserModel> result) {
        this.result = result;
    }

    private void setUserRepository(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    public void execute(String token) {
        RequestUtils.getFacebookUser(token, this);
    }

    @Override
    public void completed(HttpResponse<JsonNode> response) {
        try {
            String socialId = response.getBody().getObject().getString("id");
            UserModel findModel = findUser(socialId);

            if (findModel != null) {
                result.setResult(findModel);
                return;
            }

            UserModel userModel = new UserModel();
            userModel.setSocialId(socialId);
            userCrudRepository.save(userModel);
            result.setResult(userModel);
        } catch (JSONException e) {
            e.printStackTrace();
            result.setErrorResult("No id field");
        }
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

    private UserModel findUser(@NotNull String socialId) {
        List<UserModel> list = userCrudRepository.findByUserBySocialId(socialId);
        return list.get(0);
    }
}
