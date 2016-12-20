package com.github.sasd97.services;

import com.github.sasd97.errors.NotFoundError;
import com.github.sasd97.errors.UnhandledError;
import com.github.sasd97.events.ParserResultListener;
import com.github.sasd97.models.AuthorizationModel;
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
import java.util.List;

/**
 * Created by Alexadner Dadukin on 11/29/2016.
 */

public class FacebookParseService implements Callback<JsonNode> {

    private static FacebookParseService facebookParseService;

    private UserRepository userRepository;
    private AuthorizationRepository authorizationRepository;

    private ParserResultListener<UserModel> listener;

    private FacebookParseService() {
    }

    public static FacebookParseService getInstance(@NotNull ParserResultListener<UserModel> listener,
                                                   @NotNull UserRepository userRepository,
                                                   @NotNull AuthorizationRepository authorizationRepository) {
        if (facebookParseService == null) {
            facebookParseService = new FacebookParseService();
        }

        facebookParseService.addListener(listener);
        facebookParseService.setRepositories(userRepository, authorizationRepository);
        return facebookParseService;
    }

    private void addListener(@NotNull ParserResultListener<UserModel> listener) {
        this.listener = listener;
    }

    public void execute(String token) {
        RequestUtils.getFacebookUser(token, this);
    }

    @Override
    public void completed(HttpResponse<JsonNode> response) {
        try {
            String socialId = response.getBody().getObject().getString("id");

            String firstName = null;
            if (response.getBody().getObject().has("first_name"))
                firstName = response.getBody().getObject().getString("first_name");

            UserModel findModel = findUser(socialId);

            if (findModel != null) {
                setAccessToken(findModel);
                listener.onSuccess(findModel);
                return;
            }

            listener.onSuccess(createUser(socialId, firstName));
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(new NotFoundError());
        }
    }

    @Override
    public void failed(UnirestException e) {
        e.printStackTrace();
        listener.onError(new NotFoundError());
    }

    @Override
    public void cancelled() {
        listener.onError(new UnhandledError());
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

    private UserModel createUser(@NotNull String socialId, String firstName) {
        UserModel userModel = new UserModel();
        userModel.setSocialId(socialId);
        userModel.setRole(UserModel.Role.USER);
        if (firstName != null) userModel.setFirstName(firstName);

        userRepository.save(userModel);

        AuthorizationModel authorizationModel = new AuthorizationModel(userModel);
        authorizationModel.setToken(socialId);
        authorizationRepository.save(authorizationModel);

        userModel.setAccessToken(authorizationModel.getToken());
        return userModel;
    }
}
