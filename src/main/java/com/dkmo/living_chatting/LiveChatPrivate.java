// package com.dkmo.living_chatting;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.messaging.handler.annotation.DestinationVariable;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.stereotype.Controller;
//
// @Controller
// public class LiveChatPrivate {
// @Autowired
// private SimpMessagingTemplate simpMessagingTemplate;
// @Autowired
// private UsersRepository usersRepository;
//
// @MessageMapping("/chat/private/{email}")
// public void privateMessage(@DestinationVariable String email, MessageDto
// message) {
// simpMessagingTemplate.convertAndSendToUser(email, "/queue/message", message);
// }
// }
