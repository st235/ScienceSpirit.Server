package com.github.sasd97.controllers;

import com.github.sasd97.repositories.AuthorizationRepository;
import com.github.sasd97.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        return new ModelAndView(
                createExternalRedirect("https://www.facebook.com/v2.8/dialog/oauth?client_id=326544131066078&redirect_uri=http://localhost:3000/webregister/redirect/facebook")
        );
    }

    @RequestMapping(value = FACEBOOK_REDIRECT,
            method = RequestMethod.GET)
    public int registerFacebook(@RequestParam("code") String code) {
        return 0;
    }
}
