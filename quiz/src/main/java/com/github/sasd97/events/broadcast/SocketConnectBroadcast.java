package com.github.sasd97.events.broadcast;

import com.github.sasd97.models.AuthorizationModel;
import com.github.sasd97.models.SocketModel;
import com.github.sasd97.repositories.AuthorizationRepository;
import com.github.sasd97.repositories.SocketRepository;
import com.github.sasd97.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexadner Dadukin on 12/19/2016.
 */

@Repository
public class SocketConnectBroadcast implements ApplicationListener<SessionConnectedEvent> {

    private final TokenUtils tokenUtils;
    private final SocketRepository socketRepository;
    private final AuthorizationRepository authorizationRepository;

    private static final Logger logger = LoggerFactory.getLogger(SocketConnectBroadcast.class.getName());

    @Autowired
    public SocketConnectBroadcast(TokenUtils tokenUtils,
                                  SocketRepository socketRepository,
                                  AuthorizationRepository authorizationRepository) {
        this.tokenUtils = tokenUtils;
        this.socketRepository = socketRepository;
        this.authorizationRepository = authorizationRepository;
    }


    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

        try {
            MessageHeaders messageHeaders = event.getMessage().getHeaders();
            GenericMessage genericMessage = (GenericMessage) messageHeaders.get("simpConnectMessage");
            Map<String, Object> map = (Map<String, Object>) genericMessage.getHeaders().get("nativeHeaders");

            String token = tokenUtils.trimSocketToken(map.get("message").toString());

            List<AuthorizationModel> authorizations = authorizationRepository.findByToken(token);
            if (authorizations.size() != 1) return;

            AuthorizationModel auth = authorizations.get(0);
            SocketModel socketModel = new SocketModel();

            socketModel.setSession(sha.getSessionId());
            socketModel.setUser(auth.getUser());

            socketRepository.save(socketModel);

            logger.info("Session connected: {} with passcode: {}", sha.getSessionId(), token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
