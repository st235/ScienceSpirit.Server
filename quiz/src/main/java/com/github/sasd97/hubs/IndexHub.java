package com.github.sasd97.hubs;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

/**
 * Created by Alexadner Dadukin on 12/9/2016.
 */

@Controller
public class IndexHub {

    @MessageMapping("/hello")
    @SendTo("/hello/app")
    public String hello() {
        Logger.getLogger(IndexHub.class.getName()).info("Hello world");
        return "Hello World";
    }
}
