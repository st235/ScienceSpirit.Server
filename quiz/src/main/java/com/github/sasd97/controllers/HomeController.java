package com.github.sasd97.controllers;

import com.github.sasd97.models.UserModel;
import com.github.sasd97.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.github.sasd97.constants.MethodConstants.Home.*;

/**
 * Created by Alexadner Dadukin on 11/20/2016.
 */

@RestController
@RequestMapping(INDEX)
public class HomeController {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @RequestMapping(value = ADD,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.GET)
    public UserModel Add() {
        UserModel userModel = new UserModel();
        userModel.setFirstName("Alexander");
        userCrudRepository.save(userModel);
        return userModel;
    }

    @RequestMapping(value = GET,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.GET)
    public List<UserModel> Get() {
        List<UserModel> result = new ArrayList<>();
        for (UserModel user: userCrudRepository.findAll()) result.add(user);
        return result;
    }
}
