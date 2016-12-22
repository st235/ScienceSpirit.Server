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
public class AdminUtils {

    private AuthorizationRepository authorizationRepository;

    @Autowired
    public AdminUtils(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    public boolean isAdminToken(@NotNull String token) {
        List<AuthorizationModel> authorization  = authorizationRepository.findAllByToken(token);
        return !(authorization == null || authorization.size() > 1 || authorization.size() == 0);
    }
}
