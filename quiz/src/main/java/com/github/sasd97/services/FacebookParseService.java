package com.github.sasd97.services;

import com.github.sasd97.errors.NotFoundError;
import com.github.sasd97.models.AuthorizationModel;
import com.github.sasd97.models.UserModel;
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

    private DeferredResult<BaseResponseModel<?>> result;
    private static FacebookParseService facebookParseService;

    private FacebookParseService() {}

    public static FacebookParseService getInstance(@NotNull DeferredResult<BaseResponseModel<?>> result,
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
            UserModel findModel = findUser(socialId);

            if (findModel != null) {
                setAccessToken(findModel);
                result.setResult(
                        new BaseResponseModel<>(findModel).success()
                );
                return;
            }

            result.setResult(
                    new BaseResponseModel<>(createUser(socialId)).success()
            );
        } catch (JSONException e) {
            e.printStackTrace();
            result.setErrorResult(new NotFoundError());
        }
    }

    @Override
    public void failed(UnirestException e) {
        e.printStackTrace();
        result.setErrorResult(new NotFoundError());
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

    private void setAccessToken(@NotNull UserModel user) {
        AuthorizationModel authorizationModel = new AuthorizationModel(user);
        authorizationModel.setToken(user.getSocialId());
        authorizationRepository.save(authorizationModel);
        user.setAccessToken(authorizationModel.getToken());
    }

    private UserModel findUser(@NotNull String socialId) {
        List<UserModel> list = userRepository.findByUserBySocialId(socialId);
        return list.size() == 0 ? null : list.get(0);
    }

    private UserModel createUser(@NotNull String socialId) {
        UserModel userModel = new UserModel();
        userModel.setSocialId(socialId);

        userRepository.save(userModel);

        AuthorizationModel authorizationModel = new AuthorizationModel(userModel);
        authorizationModel.setToken(socialId);
        authorizationRepository.save(authorizationModel);

        userModel.setAccessToken(authorizationModel.getToken());
        return userModel;
    }
}
