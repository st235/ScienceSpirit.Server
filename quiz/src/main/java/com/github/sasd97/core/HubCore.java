package com.github.sasd97.core;

import com.github.sasd97.configs.WebSocketConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by Alexadner Dadukin on 12/9/2016.
 */

@Configuration
@EnableWebSocketMessageBroker
public class HubCore extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/hello"); // prefix to outcoming messages
        config.setApplicationDestinationPrefixes("/app"); // prefix to incoming messages
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/sockets/sockjs")
                .setAllowedOrigins("*")
                .withSockJS()
                .setInterceptors(socketChanelInterceptor());
    }

    @Bean
    public WebSocketConfig socketChanelInterceptor() {
        return new WebSocketConfig();
    }
}
