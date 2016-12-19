package com.github.sasd97.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.ExpiringSession;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by Alexadner Dadukin on 12/9/2016.
 */

@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class HubCore extends AbstractSessionWebSocketMessageBrokerConfigurer<ExpiringSession> {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/hello"); // prefix to outcoming messages
        config.setApplicationDestinationPrefixes("/app"); // prefix to incoming messages
    }

    @Override
    public void configureStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/sockets/sockjs")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
