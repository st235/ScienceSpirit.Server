package com.github.sasd97.services;

import com.github.sasd97.models.AuthorizationModel;
import com.github.sasd97.models.UserModel;
import com.github.sasd97.repositories.AuthorizationRepository;
import com.github.sasd97.repositories.UserRepository;
import com.github.sasd97.utils.HashUtils;
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

    private UserRepository userRepository;
    private AuthorizationRepository authorizationRepository;

    private DeferredResult<UserModel> result;
    private static FacebookParseService facebookParseService;

    private FacebookParseService() {}

    public static FacebookParseService getInstance(@NotNull DeferredResult<UserModel> result,
                                                   @NotNull UserRepository userRepository,
                                                   @NotNull AuthorizationRepository authorizationRepository) {
        if (facebookParseService == null) {
            facebookParseService = new FacebookParseService();
        }

        facebookParseService.setAsyncHandler(result);
        facebookParseService.setRepositories(userRepository, authorizationRepository);

        return facebookParseService;
    }

    public void execute(String token) {
        RequestUtils.getFacebookUser(token, this);
    }

    @Override
    public void completed(HttpResponse<JsonNode> response) {
        try {
            String socialId = response.getBody().getObject().getString("id");
            System.out.println(socialId);

            UserModel findModel = findUser(socialId);

            if (findModel != null) {
                result.setResult(findModel);
                return;
            }

            result.setResult(createUser(socialId));
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

    private void setAsyncHandler(@NotNull DeferredResult<UserModel> result) {
        this.result = result;
    }

    private void setRepositories(UserRepository userRepository,
                                 AuthorizationRepository authorizationRepository) {
        this.userRepository = userRepository;
        this.authorizationRepository = authorizationRepository;
    }

    private AuthorizationModel findAccessToken(@NotNull Long userId) {
        return null; //TODO: add this field
    }

    private UserModel findUser(@NotNull String socialId) {
        List<UserModel> list = userRepository.findByUserBySocialId(socialId);
        return list.size() == 0 ? null : list.get(0);
    }

    private UserModel createUser(@NotNull String socialId) {
        UserModel userModel = new UserModel();
        userModel.setSocialId(socialId);
        System.out.println("Hello world#12");

        userRepository.save(userModel);
        System.out.println("Hello world");

        AuthorizationModel authorizationModel = new AuthorizationModel(userModel);
        authorizationModel.setToken(HashUtils.md5(socialId));
        authorizationRepository.save(authorizationModel);

        userModel.setAccessToken(authorizationModel.getToken());
        return userModel;
    }
}
