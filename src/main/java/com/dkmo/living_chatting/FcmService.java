package com.dkmo.living_chatting;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class FcmService {

    private static final String PROJECT_ID = "livechat-ce9c4";
    private static final String FCM_URL = "https://fcm.googleapis.com/v1/projects/" + PROJECT_ID + "/messages:send";

    private final RestTemplate restTemplate = new RestTemplate();

    private String getAccessToken() throws Exception {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream("livechat-ce9c4-firebase-adminsdk-fbsvc-3f6b921c50.json"))
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/firebase.messaging"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    public void sendMessage(String targetToken, String title, String body) throws Exception {
        Map<String, Object> notification = new HashMap<>();
        notification.put("title", title);
        notification.put("body", body);

        Map<String, Object> message = new HashMap<>();
        message.put("token", targetToken);
        message.put("notification", notification);

        Map<String, Object> request = new HashMap<>();
        request.put("message", message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getAccessToken());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        restTemplate.postForEntity(FCM_URL, entity, String.class);
    }
}
