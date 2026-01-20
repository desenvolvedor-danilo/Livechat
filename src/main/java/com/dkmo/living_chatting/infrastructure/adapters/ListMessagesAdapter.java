package com.dkmo.living_chatting.infrastructure.adapters;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.dkmo.living_chatting.domain.model.Message;

import lombok.Data;
@Data
public class ListMessagesAdapter {
    private String to;
    private String from;
    private String message;
    private String time;
    private String user;
 

/**
     * @param to
     * @param from
     * @param message
     * @param time
     * @param user
     */
    public ListMessagesAdapter(String to, String from, String message, String time, String user) {
        this.to = to;
        this.from = from;
        this.message = message;
        this.time = time;
        this.user = user;
    }


public static ListMessagesAdapter convertResponse(Message message)   {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm")
    .withZone(ZoneId.of("America/Sao_Paulo"));
    return new ListMessagesAdapter(message.getTo(), message.getFrom(), message.getMessage(), formatter.format(message.getNow()),message.getUser());
  } 
}
