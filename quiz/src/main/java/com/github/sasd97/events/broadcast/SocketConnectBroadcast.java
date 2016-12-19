package com.github.sasd97.events.broadcast;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * Created by Alexadner Dadukin on 12/19/2016.
 */

@Repository
public class SocketConnectBroadcast implements ApplicationListener<SessionConnectedEvent> {

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        System.out.printf("Session connected: %1$s\n", sha.getSessionId());
    }
}
