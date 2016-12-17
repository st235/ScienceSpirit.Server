package com.github.sasd97.controllers;

import com.github.sasd97.models.reponse.BaseResponseModel;
import com.github.sasd97.repositories.AuthorizationRepository;
import com.github.sasd97.repositories.UserRepository;
import com.github.sasd97.services.FacebookParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import static com.github.sasd97.constants.MethodConstants.Registration.FACEBOOK;
import static com.github.sasd97.constants.MethodConstants.Registration.INDEX;

/**
 * Created by Alexadner Dadukin on 11/20/2016.
 */

@RestController
@RequestMapping(INDEX)
public class RegistrationController {

    private final UserRepository userRepository;
    private final AuthorizationRepository authorizationRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository,
                                  AuthorizationRepository authorizationRepository) {
        this.userRepository = userRepository;
        this.authorizationRepository = authorizationRepository;
    }

    @RequestMapping(value = FACEBOOK,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.GET)
    public DeferredResult<BaseResponseModel<?>> Get(@PathVariable("token") String token) {
        DeferredResult<BaseResponseModel<?>> asyncTask = new DeferredResult<>();

        FacebookParseService
                .getInstance(asyncTask, userRepository, authorizationRepository)
                .execute(token);

        return asyncTask;
    }
}
