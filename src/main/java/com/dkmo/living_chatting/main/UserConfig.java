package com.dkmo.living_chatting.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.dkmo.living_chatting.application.gateway.ConversationCreateGateway;
import com.dkmo.living_chatting.application.gateway.ConversationEditGateway;
import com.dkmo.living_chatting.application.gateway.ConversationGateway;
import com.dkmo.living_chatting.application.gateway.ConversationSaveGateway;
import com.dkmo.living_chatting.application.gateway.InstantGateway;
import com.dkmo.living_chatting.application.gateway.LoadAllUsersGateway;
import com.dkmo.living_chatting.application.gateway.LoadFileGateway;
import com.dkmo.living_chatting.application.gateway.LoadMessagesGateway;
import com.dkmo.living_chatting.application.gateway.LoadNotificationGateway;
import com.dkmo.living_chatting.application.gateway.LoginPolicyGateway;
import com.dkmo.living_chatting.application.gateway.MessageGateway;
import com.dkmo.living_chatting.application.gateway.MessageSaveGateway;
import com.dkmo.living_chatting.application.gateway.UserGateway;
import com.dkmo.living_chatting.application.usecases.CreateUserInteractor;
import com.dkmo.living_chatting.application.usecases.GetPhotoProfileUseCase;
import com.dkmo.living_chatting.application.usecases.LoadAllUsersUseCase;
import com.dkmo.living_chatting.application.usecases.LoadFilesUseCase;
import com.dkmo.living_chatting.application.usecases.LoadMessageUseCase;
import com.dkmo.living_chatting.application.usecases.LoadNotificationsUseCase;
import com.dkmo.living_chatting.application.usecases.LoginPolicyInteractor;
import com.dkmo.living_chatting.application.usecases.MessageUseCase;
import com.dkmo.living_chatting.controller.adapters.UserAdapter;
import com.dkmo.living_chatting.infrastructure.gateways.CreateaIdHash;
import com.dkmo.living_chatting.infrastructure.gateways.EditUserGatewayImpl;
import com.dkmo.living_chatting.infrastructure.gateways.LoadFile;
import com.dkmo.living_chatting.infrastructure.gateways.LoadUserGateway;
import com.dkmo.living_chatting.infrastructure.gateways.SendMessage;
import com.dkmo.living_chatting.infrastructure.gateways.UserEntityMapper;
import com.dkmo.living_chatting.infrastructure.gateways.UserRepositoryGateway;
import com.dkmo.living_chatting.infrastructure.repositories.UsersRepository;

@Configuration
public class UserConfig {
    @Bean
  public CreateUserInteractor createUseCase(UserGateway userGateway){
  return new CreateUserInteractor(userGateway);
  }
  @Bean
  public UserGateway userGateway(UsersRepository usersRepository,UserEntityMapper userEntityMapper){
    return new UserRepositoryGateway(usersRepository, userEntityMapper);
  }
  @Bean
  public UserEntityMapper userEntityMapper(){
    return new UserEntityMapper();
  } 
  @Bean 
 public UserAdapter userDTOMapper(){
    return new UserAdapter();
  }
  @Bean 
  public LoginPolicyInteractor loginPolicyInteractor(LoginPolicyGateway gateway){
  return new LoginPolicyInteractor(gateway);
  }
  @Bean
  public LoadUserGateway loadUser(UsersRepository usersRepository, UserEntityMapper userEntityMapper){
return new LoadUserGateway(userEntityMapper, usersRepository);
  }
  @Bean
  public SendMessage sendMessage(SimpMessagingTemplate simpMessagingTemplate){
    return new SendMessage(simpMessagingTemplate);
  }
  @Bean
  public CreateaIdHash createaIdHash(){
    return new CreateaIdHash();
  }
  @Bean 
public  MessageUseCase messageUseCase(MessageGateway
  messageGateway,InstantGateway instantGateway,LoginPolicyGateway loginPolicyGateway,MessageSaveGateway messageSaveGateway,CreateaIdHash createaIdHash,ConversationGateway conversationGateway,ConversationCreateGateway conversationCreateGateway, ConversationSaveGateway conversationSaveGateway,ConversationEditGateway conversationEditGateway
){
    return new MessageUseCase(messageGateway, instantGateway,loginPolicyGateway, messageSaveGateway,createaIdHash,conversationGateway,conversationCreateGateway,conversationSaveGateway,conversationEditGateway);

  }
  @Bean
  public LoadMessageUseCase loadMessageUseCase(LoadMessagesGateway loadMessagesGateway){
    return new LoadMessageUseCase(loadMessagesGateway);

  }
  @Bean 
  public LoadAllUsersUseCase loadAllUsersUseCase(LoadAllUsersGateway loadAllUsersGateway){
    return new LoadAllUsersUseCase(loadAllUsersGateway);
  }
  @Bean
  EditUserGatewayImpl editUserGatewayImpl(MongoTemplate mongoTemplate){
    return new EditUserGatewayImpl(mongoTemplate);
  }
  @Bean
  public LoadNotificationsUseCase loadNotificationsUseCase(LoadNotificationGateway loadNotificationGateway){
    return new LoadNotificationsUseCase(loadNotificationGateway);
  }
  @Bean 
  public LoadFilesUseCase loadFilesUseCase(LoadFileGateway loadFileGateway,LoadUserGateway loadUserGateway,EditUserGatewayImpl
    editUserGateway){
  return new LoadFilesUseCase(loadFileGateway, loadUserGateway,editUserGateway);
  }
  @Bean
  public LoadFile loadFileGateway(){
    return new LoadFile();
  }
  @Bean
  public GetPhotoProfileUseCase getPhotoProfileUseCase(LoginPolicyGateway loginPolicyGateway){
    return new GetPhotoProfileUseCase(loginPolicyGateway);
  }
}
