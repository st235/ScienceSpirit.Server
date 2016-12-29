package com.github.sasd97.controllers;

import com.github.sasd97.errors.NotAuthorizedError;
import com.github.sasd97.models.AdminSecretModel;
import com.github.sasd97.models.AuthorizationModel;
import com.github.sasd97.models.UserModel;
import com.github.sasd97.repositories.AdminSecretRepository;
import com.github.sasd97.repositories.AuthorizationRepository;
import com.github.sasd97.repositories.UserRepository;
import com.github.sasd97.services.AdminSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.sasd97.constants.MethodConstants.AdminRegistration.INDEX;
import static com.github.sasd97.constants.MethodConstants.AdminRegistration.REGISTER;

/**
 * Created by alexander on 20.12.16.
 */

@RestController
@RequestMapping(INDEX)
public class AdminRegistrationController {

    private final UserRepository userRepository;
    private final AdminSecretRepository adminSecretRepository;
    private final AuthorizationRepository authorizationRepository;

    @Autowired
    public AdminRegistrationController(UserRepository userRepository,
                                       AuthorizationRepository authorizationRepository,
                                       AdminSecretRepository adminSecretRepository) {
        this.userRepository = userRepository;
        this.authorizationRepository = authorizationRepository;
        this.adminSecretRepository = adminSecretRepository;
    }

    @RequestMapping(value = REGISTER,
    produces = { MediaType.APPLICATION_JSON_VALUE },
    method = RequestMethod.POST)
    public UserModel registerAdmin(@RequestParam("nickname") String nickname,
                                   @RequestParam("passwordHash") String passwordHash,
                                   @RequestParam("secret") String adminSecret) {

        Iterable<AdminSecretModel> secrets = adminSecretRepository.findAll();
        boolean isSecret = AdminSecretService.isSecret(secrets, adminSecret);

        if (!isSecret) throw new NotAuthorizedError();

        String socialId = String.format("admin_%1$s", nickname);

        List<UserModel> userModel = userRepository.findByUserBySocialId(socialId);

        UserModel admin = new UserModel();
        if (userModel == null || userModel.size() == 0) {
            admin.setNickname(nickname);
            admin.setPasswordHash(passwordHash);
            admin.setRole(UserModel.Role.ADMIN);
            admin.setSocialId(socialId);
            userRepository.save(admin);
        } else {
            admin = userModel.get(0);
        }

        if (admin.getPasswordHash() == null || !admin.getPasswordHash().equals(passwordHash)) throw new NotAuthorizedError();

        AuthorizationModel authorizationModel = new AuthorizationModel(admin);
        authorizationModel.setToken(admin.getSocialId());
        authorizationRepository.save(authorizationModel);
        admin.setAccessToken(authorizationModel.getToken());

        return admin;
    }
}
