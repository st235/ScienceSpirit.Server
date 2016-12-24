package com.github.sasd97.utils;

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

    private AuthorizationRepository authorizationRepository;

    @Autowired
    public TokenUtils(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    public boolean isToken(@NotNull String token) {
        List<AuthorizationModel> authorizations = authorizationRepository.findByToken(token);
        return authorizations.size() != 0;
    }

    public boolean isAdminToken(@NotNull String token) {
        List<AuthorizationModel> authorization  = authorizationRepository.findAdminsByToken(token);
        return !(authorization == null || authorization.size() > 1 || authorization.size() == 0);
    }
}
