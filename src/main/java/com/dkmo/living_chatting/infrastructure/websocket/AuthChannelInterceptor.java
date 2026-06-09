package com.dkmo.living_chatting.infrastructure.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

public class AuthChannelInterceptor implements ChannelInterceptor {
@Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(message);

        if (accessor.getCommand() == null) {
            return message;
        }

        if (accessor.getCommand().name().equals("CONNECT")) {

            String user = accessor.getFirstNativeHeader("user");

            if (user != null) {
                accessor.setUser(() -> user);
            }
        }

        return message;
    }

}
