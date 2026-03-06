package com.dkmo.living_chatting.application.usecases;

import java.time.Instant;
import java.util.UUID;

import com.dkmo.living_chatting.application.exceptions.InvalidSenderException;
import com.dkmo.living_chatting.application.exceptions.NotificationUnauthorizedException;
import com.dkmo.living_chatting.application.gateway.ConversationCreateGateway;
import com.dkmo.living_chatting.application.gateway.ConversationEditGateway;
import com.dkmo.living_chatting.application.gateway.ConversationGateway;
import com.dkmo.living_chatting.application.gateway.ConversationSaveGateway;
import com.dkmo.living_chatting.application.gateway.CreateIdGateway;
import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.application.gateway.GenerateIdGateway;
import com.dkmo.living_chatting.application.gateway.InstantGateway;
import com.dkmo.living_chatting.application.gateway.MessageGateway;
import com.dkmo.living_chatting.application.gateway.MessageSaveGateway;
import com.dkmo.living_chatting.application.gateway.NotificationGateway;
import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.domain.model.User;

public class MessageUseCase {   
private final MessageGateway messageGateway;
private final InstantGateway instantGateway;
private final FindUserGateway loginPolicyGateway;
private final MessageSaveGateway messageSaveGateway;
private final CreateIdGateway createIdGateway;
private final ConversationGateway conversationGateway;
private final ConversationCreateGateway conversationCreateGateway;
private final ConversationSaveGateway conversationSaveGateway;
private final ConversationEditGateway conversationEditGateway;
private final NotificationGateway notificationGateway;
private final GenerateIdGateway generateIdGateway;
  /**
 * @param messageGateway
 */
public MessageUseCase(MessageGateway messageGateway, InstantGateway instantGateway,FindUserGateway loginPolicyGateway,MessageSaveGateway messageSaveGateway,CreateIdGateway createIdGateway, ConversationGateway conversationGateway, ConversationCreateGateway conversationCreateGateway, ConversationSaveGateway conversationSaveGateway,ConversationEditGateway conversationEditGateway,NotificationGateway notificationGateway,GenerateIdGateway generateIdGateway
) {
  this.messageGateway = messageGateway;
  this.instantGateway = instantGateway;
  this.loginPolicyGateway = loginPolicyGateway; 
  this.messageSaveGateway = messageSaveGateway;
  this.createIdGateway = createIdGateway;
  this.conversationGateway = conversationGateway;
  this.conversationCreateGateway = conversationCreateGateway;
  this.conversationSaveGateway = conversationSaveGateway;
  this.conversationEditGateway = conversationEditGateway;
  this.notificationGateway = notificationGateway;
  this.generateIdGateway = generateIdGateway;
}

  public void execute(String message, String from, String to,String user,String urlFile){
  User  sender = loginPolicyGateway.findByEmail(from);
  User recipient = loginPolicyGateway.findByEmail(to); 

    if(sender==null){
      throw new InvalidSenderException();
    }
  String idConversa = createIdGateway.createIdConversa(from,to);
  Instant now = instantGateway.now();
   UUID id = generateIdGateway.generateId();
   Message msg = Message.create(id, message, from, to, now,sender.nome(),idConversa,urlFile);
    messageSaveGateway.saveMessage(msg);
    messageGateway.sendMessage(msg);
    

    Conversation conversation = conversationGateway.findConversation(msg);
    if(conversation==null){ 
    conversation = conversationCreateGateway.createConversation(msg);
   conversationSaveGateway.saveConversation(conversation); 
    }else{
      conversationEditGateway.editConversation(conversation, msg);
    }
    if(recipient.token()==null){
      throw new NotificationUnauthorizedException();
    }
    if(message!=null){
    notificationGateway.sendNotification(recipient.token(), "Nova mensagem de: " +sender.nome(),message);
    }else{
    notificationGateway.sendNotification(recipient.token(), "Nova foto de: " +sender.nome(),null);
    }
  }
} 
