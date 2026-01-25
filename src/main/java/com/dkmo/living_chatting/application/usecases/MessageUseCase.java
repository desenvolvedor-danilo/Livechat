package com.dkmo.living_chatting.application.usecases;

import java.time.Instant;

import com.dkmo.living_chatting.application.exceptions.InvalidSenderException;
import com.dkmo.living_chatting.application.gateway.CreateIdGateway;
import com.dkmo.living_chatting.application.gateway.InstantGateway;
import com.dkmo.living_chatting.application.gateway.LoginPolicyGateway;
import com.dkmo.living_chatting.application.gateway.MessageGateway;
import com.dkmo.living_chatting.application.gateway.MessageSaveGateway;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.domain.model.User;

public class MessageUseCase {   
private final MessageGateway messageGateway;
private final InstantGateway instantGateway;
private final LoginPolicyGateway loginPolicyGateway;
private final MessageSaveGateway messageSaveGateway;
private final CreateIdGateway createIdGateway;
  /**
 * @param messageGateway
 */
public MessageUseCase(MessageGateway messageGateway, InstantGateway instantGateway,LoginPolicyGateway loginPolicyGateway,MessageSaveGateway messageSaveGateway,CreateIdGateway createIdGateway) {
  this.messageGateway = messageGateway;
  this.instantGateway = instantGateway;
  this.loginPolicyGateway = loginPolicyGateway; 
  this.messageSaveGateway = messageSaveGateway;
  this.createIdGateway = createIdGateway;
}

  public void execute(String message, String from, String to,String user){
  User  sender = loginPolicyGateway.findByEmail(from);
    if(sender==null){
      throw new InvalidSenderException();
    }
  String idConversa = createIdGateway.createIdConversa(from,to);
  Instant now = instantGateway.now();
   Message msg = Message.create(message, from, to, now,sender.nome(),idConversa);
    messageSaveGateway.saveMessage(msg);
    messageGateway.sendMessage(msg);
  }
} 
