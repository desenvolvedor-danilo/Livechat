package com.dkmo.living_chatting.infrastructure.adapters;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.dkmo.living_chatting.infrastructure.persistence.MessagesPrivate;

public class InstantAdapter {
public static String TimeAdapter(MessagesPrivate message)   {
        
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm")
    .withZone(ZoneId.of("America/Sao_Paulo"));
    return formatter.format(message.getTimeStamp());
  } 
}
