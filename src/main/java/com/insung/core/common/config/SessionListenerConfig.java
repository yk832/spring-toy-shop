package com.insung.core.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
@Configuration
public class SessionListenerConfig implements HttpSessionListener {

    private static final int SESSION_TIMEOUT = 300; // sec
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session Destroyed!!");
    }
}
