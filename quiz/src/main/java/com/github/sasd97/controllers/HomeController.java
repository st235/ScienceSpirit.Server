package com.github.sasd97.controllers;

import com.github.sasd97.models.UserModel;
import com.github.sasd97.repositories.UserCrudRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alexadner Dadukin on 11/20/2016.
 */

@RestController
@RequestMapping("/user")
public class HomeController {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String Add() {
        UserModel userModel = new UserModel("Alexander", "Dadukin");
        userCrudRepository.save(userModel);
        Gson gson = new Gson();
        return gson.toJson(userModel);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Iterable<UserModel> Get() {
        return userCrudRepository.findAll();
    }
}
