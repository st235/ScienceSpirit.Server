package com.github.sasd97.hubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by Alexadner Dadukin on 12/9/2016.
 */

@Controller
public class GameHub {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public GameHub(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

//    @Scheduled(fixedDelay = 1000)
//    public void hello() {
//        if (SocketConnectBroadcast.id == null) return;
//
//        System.out.print("Here");
//
//        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
//                .create(SimpMessageType.MESSAGE);
//        headerAccessor.setSessionId(SocketConnectBroadcast.id);
//        headerAccessor.setLeaveMutable(true);
//
//        simpMessagingTemplate.convertAndSendToUser(SocketConnectBroadcast.id,"/hello/app", "helloWorld" + SocketConnectBroadcast.id,
//                headerAccessor.getMessageHeaders());
//    }
}
