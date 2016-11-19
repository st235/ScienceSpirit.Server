package com.github.sasd97.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alexadner Dadukin on 11/20/2016.
 */

@RestController
public class HomeController {

    @RequestMapping(name = "/hello", method = RequestMethod.GET)
    public String Hello() {
        return "Hello world!";
    }
}
