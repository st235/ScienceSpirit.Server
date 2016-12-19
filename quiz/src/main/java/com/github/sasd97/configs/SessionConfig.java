package com.github.sasd97.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

/**
 * Created by alexander on 19.12.16.
 */

@EnableSpringHttpSession
@Configuration
public class SessionConfig {

    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository();
    }
}
