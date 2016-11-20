package com.github.sasd97.controllers;

import com.github.sasd97.database.User;
import com.github.sasd97.database.UserCrud;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexadner Dadukin on 11/20/2016.
 */

@RestController
@RequestMapping("/user")
public class HomeController {

    @Autowired
    private UserCrud userCrud;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String Add() {
        User user = new User("Alexander", "Dadukin");
        userCrud.save(user);
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Iterable<User> Get() {
        return userCrud.findAll();
    }
}
