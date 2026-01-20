package com.dkmo.living_chatting.infrastructure.adapters;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.dkmo.living_chatting.infrastructure.persistence.MessagesPrivate;

public class InstantToNormalHour 
{
  public static String messagesPrivate(MessagesPrivate message){ 
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm")
    .withZone(ZoneId.of("America/Sao_Paulo"));
    return formatter.format(message.getTimeStamp());
  }
}
