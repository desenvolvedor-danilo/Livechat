package com.dkmo.living_chatting;




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
    int id = 0;

    @MessageMapping("/new-message")
    @SendTo("/topics/livechat")
    public ChatOutput newMessage(MessageDto message) {
        id++;
        String user = usersService.findUsersForEmail(message.email());
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

        return ChatOutput.builder().idMensagem(message.id()).from(user).content(message.message()).build();

    }
}
