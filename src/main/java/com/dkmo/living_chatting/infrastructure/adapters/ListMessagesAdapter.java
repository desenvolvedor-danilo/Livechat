package com.dkmo.living_chatting.infrastructure.adapters;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.dkmo.living_chatting.domain.model.Message;

import lombok.Data;

@Data
public class ListMessagesAdapter {
    private UUID id;
    private String to;
    private String from;
    private String message;
    private String time;
    private String user;
    private String url;

    /**
     * @param to
     * @param from
     * @param message
     * @param time
     * @param user
     */
    public ListMessagesAdapter(UUID id, String to, String from, String message, String time, String user) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.message = message;
        this.time = time;
        this.user = user;
    }

    public static ListMessagesAdapter convertResponse(Message message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm")
                .withZone(ZoneId.of("America/Sao_Paulo"));
        ListMessagesAdapter listMessagesAdapter = new ListMessagesAdapter(message.getId(), message.getTo(),
                message.getFrom(),
                message.getMessage(), formatter.format(message.getNow()), message.getUser());
        listMessagesAdapter.url = message.getUrlFile() != null ? message.getUrlFile() : null;
        return listMessagesAdapter;
    }
}
