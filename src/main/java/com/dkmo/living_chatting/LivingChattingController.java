package com.dkmo.living_chatting;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LivingChattingController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private FcmService fcmService;
    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/new-message")
    @SendTo("/topics/livechat")
    public ChatOutput newMessage(MessageDto message) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String user = usersService.findUsersForEmail(message.email());
        LocalTime agora = LocalTime.now(ZoneId.of("America/Sao_Paulo"));

        new Thread(new Runnable() {
            @Override
            public void run() {

                usersService.findAllUsers().forEach(u -> {
                    if (u.getFcmToken() != null && !u.getEmail().equals(message.email())) {
                        try {
                            fcmService.sendMessage(u.getFcmToken(),
                                    "Nova mensagem de " + user,
                                    message.message());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (!message.message().equals("")) {
                    MessageModel messageModel = new MessageModel();
                    messageModel.setEmail(message.email());
                    messageModel.setTimeStamp(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).getHour()
                            + ":"
                            + ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).getMinute());
                    messageModel.setId(UUID.randomUUID().toString());
                    messageModel.setUsername(user);
                    messageModel.setMessage(message.message());
                    messageRepository.save(messageModel);
                }

            }
        }).start();
        return ChatOutput.builder().idMensagem(message.id()).from(user).content(message.message())
                .timeStamp(agora.format(dateTimeFormatter))
                .build();

    }
}
