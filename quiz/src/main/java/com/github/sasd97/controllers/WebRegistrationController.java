package com.github.sasd97.controllers;

import com.github.sasd97.errors.IllegalArgumentError;
import com.github.sasd97.models.reponse.BaseResponseModel;
import com.github.sasd97.repositories.AuthorizationRepository;
import com.github.sasd97.repositories.UserRepository;
import com.github.sasd97.services.WebCodeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import static com.github.sasd97.constants.MethodConstants.WebRegistration.FACEBOOK;
import static com.github.sasd97.constants.MethodConstants.WebRegistration.FACEBOOK_REDIRECT;
import static com.github.sasd97.constants.MethodConstants.WebRegistration.INDEX;
import static com.github.sasd97.utils.RequestUtils.createExternalRedirect;

/**
 * Created by Alexadner Dadukin on 12/18/2016.
 */

@RestController
@RequestMapping(INDEX)
public class WebRegistrationController {

    private final String FACEBOOK_REDIRECT_URL = "http://localhost:3000/webregister/redirect/facebook";

    private final UserRepository userRepository;
    private final AuthorizationRepository authorizationRepository;

    @Autowired
    public WebRegistrationController(UserRepository userRepository,
                                  AuthorizationRepository authorizationRepository) {
        this.userRepository = userRepository;
        this.authorizationRepository = authorizationRepository;
    }

    @RequestMapping(value = FACEBOOK,
            method = RequestMethod.GET)
    public ModelAndView redirectFacebook() {
        String facebookUrl =
                String.format("https://www.facebook.com/v2.8/dialog/oauth?client_id=326544131066078&redirect_uri=%1$s",
                        FACEBOOK_REDIRECT_URL);

        return new ModelAndView(createExternalRedirect(facebookUrl));
    }

    @RequestMapping(value = FACEBOOK_REDIRECT,
            method = RequestMethod.GET)
    public DeferredResult<BaseResponseModel<?>> registerFacebook(@RequestParam("code") String code) {
        DeferredResult<BaseResponseModel<?>> result = new DeferredResult<>();

        System.out.print(code);
        System.out.print(code.equalsIgnoreCase(""));


        if (code.equalsIgnoreCase("")){
            result.setErrorResult(new IllegalArgumentError());
            return result;
        }

        WebCodeRequestService
                .getInstance(result, userRepository, authorizationRepository)
                .code(code, FACEBOOK_REDIRECT_URL);

        return result;
    }
}
