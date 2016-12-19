package com.github.sasd97.events.broadcast;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by Alexadner Dadukin on 12/19/2016.
 */

@Repository
public class SocketDisconnectBroadcast implements ApplicationListener<SessionDisconnectEvent> {

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        System.out.printf("Session disconnected: %1$s\n", sha.getSessionId());
    }
}
