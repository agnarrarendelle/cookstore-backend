package com.huangbusiness.service.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashSet;
import java.util.Set;

@Component
public class MyWebSocketHandler {
    final private Set<String> sessionIds = new HashSet<>();
    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        String sessionId = event.getMessage().getHeaders().get("simpSessionId", String.class);
        sessionIds.add(sessionId);
        System.out.println("WS CONNECTED");
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        sessionIds.remove(event.getSessionId());
        System.out.println("WS DISCONNECTED");
    }

    public boolean isAdminConnected(){
        return !sessionIds.isEmpty();
    }
}
