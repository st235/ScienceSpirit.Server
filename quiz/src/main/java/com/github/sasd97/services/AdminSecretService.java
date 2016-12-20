package com.github.sasd97.services;

import com.github.sasd97.models.AdminSecretModel;
import com.github.sasd97.repositories.AdminSecretRepository;
import com.github.sasd97.utils.HashUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alexander on 20.12.16.
 */

@Service
public class AdminSecretService {

    private Logger logger = LoggerFactory.getLogger(AdminSecretService.class);

    private AdminSecretRepository adminSecretRepository;

    @Autowired
    public AdminSecretService(AdminSecretRepository adminSecretRepository) {
        this.adminSecretRepository = adminSecretRepository;
    }

    @PostConstruct
    public void initSecret() {
        adminSecretRepository.deleteAll();

        String secret = HashUtils.randomMd5();
        AdminSecretModel model = new AdminSecretModel();

        model.setSecret(secret);

        adminSecretRepository.save(model);

        logger.info("Secret admin hashsum is: {}", secret);
    }

    public static boolean isSecret(@NotNull Iterable<AdminSecretModel> models, @NotNull String data) {
        boolean result = false;

        for (AdminSecretModel secret: models) {
            if (secret.getSecret().equals(data)) result = true;
        }

        return result;
    }
}
