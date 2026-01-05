package com.dkmo.living_chatting;

import java.security.Principal;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LivingChattingController {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UsersService usersService;
	@Autowired
	private FcmService fcmService;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private MessagePrivateService messagePrivateService;
	@Autowired
	private FilesService filesService;



	@MessageMapping("/new-message")
	@SendTo("/topics/livechat")
	public ChatOutput newMessage(MessageDto message) {

DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		String user = usersService.findUsersForEmail(message.from());
		LocalTime agora = LocalTime.now(ZoneId.of("America/Sao_Paulo"));

		new Thread(new Runnable() {
			@Override
			public void run() {

				usersService.findAllUsers().forEach(u -> {
					if (u.getFcmToken() != null && !u.getEmail().equals(message.from())) {
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
					messageModel.setEmail(message.from());
					messageModel.setTimeStamp(agora.format(dateTimeFormatter));
					messageModel.setId(UUID.randomUUID().toString());
					messageModel.setUsername(user);
					messageModel.setMessage(message.message());
					messageRepository.save(messageModel);
				}

			}
		}).start();
		return ChatOutput.builder().from(user).content(message.message())
				.timeStamp(agora.format(dateTimeFormatter))
				.build();

	}

	@MessageMapping("/chat/private/")
	public void privateMessage(@Payload Messages message, Principal principal) {

DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    LocalTime now = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
    message.setTimeStamp(now.format(dateTimeFormatter));
    UserModel user = usersRepository.findByEmail(message.getTo());
		if (user.getFcmToken() != null && user.getEmail() != message.getFrom()) {
			try {
				fcmService.sendMessage(user.getFcmToken(), "Nova mensagem de: " + user.getUsuario(), message.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		message.setFrom(principal.getName());

		UserModel userModel = usersRepository.findByEmail(message.getFrom());
     
		message.setUser(userModel.getUsuario());
	  message.setName(userModel.getName());
	  // if(message.getFile()!=null){
	  // message.setUrlFile(filesService.fileUrl(message.getFile()));
	  // message.setFile(null);
	  // }
	 System.out.println(message.getUrlFile());
		messagePrivateService.saveMessages(message);
		simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue/message", message);
	}
}
