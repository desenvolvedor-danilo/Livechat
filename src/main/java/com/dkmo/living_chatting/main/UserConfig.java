package com.dkmo.living_chatting.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dkmo.living_chatting.application.gateway.ConversationCreateGateway;
import com.dkmo.living_chatting.application.gateway.ConversationEditGateway;
import com.dkmo.living_chatting.application.gateway.ConversationGateway;
import com.dkmo.living_chatting.application.gateway.ConversationSaveGateway;
import com.dkmo.living_chatting.application.gateway.EncryptBase64Gateway;
import com.dkmo.living_chatting.application.gateway.EncryptPasswordGateway;
import com.dkmo.living_chatting.application.gateway.FcmTokenGateway;
import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.application.gateway.GenerateIdGateway;
import com.dkmo.living_chatting.application.gateway.InstantGateway;
import com.dkmo.living_chatting.application.gateway.LoadAllUsersGateway;
import com.dkmo.living_chatting.application.gateway.LoadFileGateway;
import com.dkmo.living_chatting.application.gateway.LoadMessagesGateway;
import com.dkmo.living_chatting.application.gateway.LoadNotificationGateway;
import com.dkmo.living_chatting.application.gateway.MessageGateway;
import com.dkmo.living_chatting.application.gateway.MessageSaveGateway;
import com.dkmo.living_chatting.application.gateway.NotificationGateway;
import com.dkmo.living_chatting.application.gateway.UserGateway;
import com.dkmo.living_chatting.application.usecases.ConverterBase64;
import com.dkmo.living_chatting.application.usecases.CreateUserInteractor;
import com.dkmo.living_chatting.application.usecases.FcmTokenUseCase;
import com.dkmo.living_chatting.application.usecases.FindUserDetailsByEmail;
import com.dkmo.living_chatting.application.usecases.GetPhotoProfileUseCase;
import com.dkmo.living_chatting.application.usecases.ImagesUseCases;
import com.dkmo.living_chatting.application.usecases.LoadAllUsersUseCase;
import com.dkmo.living_chatting.application.usecases.LoadFilesUseCase;
import com.dkmo.living_chatting.application.usecases.LoadMessageUseCase;
import com.dkmo.living_chatting.application.usecases.LoadNotificationsUseCase;
import com.dkmo.living_chatting.application.usecases.LoadUserUseCase;
import com.dkmo.living_chatting.application.usecases.MessageUseCase;
import com.dkmo.living_chatting.controller.adapters.UserAdapter;
import com.dkmo.living_chatting.infrastructure.gateways.ConverterBase64Impl;
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
  public CreateUserInteractor createUseCase(UserGateway userGateway,EncryptPasswordGateway encryptPasswordGateway){
  return new CreateUserInteractor(userGateway,encryptPasswordGateway);
  }
  @Bean
  public ImagesUseCases imagesUseCases(LoadFileGateway loadFileGateway){
    return new ImagesUseCases(loadFileGateway);
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
  // @Bean 
  // public LoginPolicyInteractor loginPolicyInteractor(FindUserGateway gateway){
  // return new LoginPolicyInteractor(gateway);
  // }
  @Bean
  public ConverterBase64 converterBase64(EncryptBase64Gateway encryptBase64Gateway){
  return new ConverterBase64(encryptBase64Gateway);
  }
 
  @Bean
  public EncryptBase64Gateway encryptBase64Gateway(){
  return new ConverterBase64Impl();
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
  messageGateway,InstantGateway instantGateway,FindUserGateway loginPolicyGateway,MessageSaveGateway messageSaveGateway,CreateaIdHash createaIdHash,ConversationGateway conversationGateway,ConversationCreateGateway conversationCreateGateway, ConversationSaveGateway conversationSaveGateway,ConversationEditGateway conversationEditGateway,NotificationGateway notificationGateway,GenerateIdGateway generateIdGateway
){
    return new MessageUseCase(messageGateway, instantGateway,loginPolicyGateway, messageSaveGateway,createaIdHash,conversationGateway,conversationCreateGateway,conversationSaveGateway,conversationEditGateway,notificationGateway, generateIdGateway);

  }
  @Bean
  public LoadMessageUseCase loadMessageUseCase(LoadMessagesGateway loadMessagesGateway){
    return new LoadMessageUseCase(loadMessagesGateway);

  }
  @Bean 
  public FcmTokenUseCase fcmTokenUseCase(FcmTokenGateway fcmTokenGateway){
  return new FcmTokenUseCase(fcmTokenGateway); 
  }
  @Bean 
  public LoadAllUsersUseCase loadAllUsersUseCase(LoadAllUsersGateway loadAllUsersGateway){
    return new LoadAllUsersUseCase(loadAllUsersGateway);
  }
  @Bean
  public LoadUserUseCase loadUserUseCase(com.dkmo.living_chatting.application.gateway.LoadUserGateway loadUserGateway){
    return new LoadUserUseCase(loadUserGateway);
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
  public GetPhotoProfileUseCase getPhotoProfileUseCase(FindUserGateway loginPolicyGateway){
    return new GetPhotoProfileUseCase(loginPolicyGateway);
  }

   
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
      return authenticationConfiguration.getAuthenticationManager();
  }
  @Bean
  public FindUserDetailsByEmail findUserDetailsByEmail
  (FindUserGateway findUserGateway){
    return new FindUserDetailsByEmail(findUserGateway);
  }
}
