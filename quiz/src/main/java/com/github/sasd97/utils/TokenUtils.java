package com.github.sasd97.utils;

import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.AuthorizationModel;
import com.github.sasd97.repositories.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */

@Service
public class TokenUtils {

    //TODO: made a refactor
    private AuthorizationRepository authorizationRepository;

    @Autowired
    public TokenUtils(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    public AuthorizationModel getToken(@NotNull String token) {
        List<AuthorizationModel> authorizations = authorizationRepository.findByToken(token);
        if (authorizations.size() == 0) throw new NotAuthorizedError();
        AuthorizationModel auth = authorizations.get(0);
        if (!holdAuth(auth)) throw new NotAuthorizedError();
        return auth;
    }


    public boolean withToken(@NotNull String token, @NotNull Long id) {
        List<AuthorizationModel> authorizations = authorizationRepository.findByToken(token);
        if (authorizations.size() == 0) return false;
        if (!holdAuth(authorizations.get(0))) return false;
        if (!authorizations.get(0).getUserId().equals(id)) return false;
        return true;
    }

    public boolean isToken(@NotNull String token) {
        List<AuthorizationModel> authorizations = authorizationRepository.findByToken(token);
        if (authorizations.size() == 0) return false;
        if (!holdAuth(authorizations.get(0))) return false;
        return true;
    }

    public boolean isAdminToken(@NotNull String token) {
        List<AuthorizationModel> authorization = authorizationRepository.findAdminsByToken(token);
        if (authorization.size() == 0) return false;
        if (!holdAuth(authorization.get(0))) return false;
        return true;
    }

    public String trimSocketToken(@NotNull String socketToken) {
        return socketToken.substring(1, socketToken.length() - 1);
    }

    private boolean holdAuth(@NotNull AuthorizationModel auth) {
        if (auth == null) return false;
        Long nowDate = DateUtils.timestamp();
        if (auth.getExpirationDate() > nowDate) return true;
        authorizationRepository.delete(auth);
        return false;
    }
}
