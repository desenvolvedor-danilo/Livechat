package com.dkmo.living_chatting.infrastructure.gateways;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dkmo.living_chatting.application.gateway.NotificationGateway;
import com.google.auth.oauth2.GoogleCredentials;

@Component
public class NotificationGatewayImpl implements NotificationGateway {

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

    // @Override
    // public void sendNotification(String tokenTarget, String title, String body,
    // String email) {
    // try {
    //
    // Map<String, Object> notification = new HashMap<>();
    // //
    // notification.put("title", title);
    // if (body.startsWith("http://") || body.startsWith("https://")) {
    // notification.put("image", body);
    // } else {
    // notification.put("body", body);
    // }
    // Map<String, Object> message = new HashMap<>();
    // Map<String, Object> fcmOptions = new HashMap<>();
    // fcmOptions.put("link",
    // "https://speakflowchat.vercel.app/chat?user=" + email + "&openAt=" +
    // System.currentTimeMillis());
    // System.out.println(fcmOptions);
    // Map<String, Object> webPush = new HashMap<>();
    //
    // webPush.put("fcm_options", fcmOptions);
    // message.put("token", tokenTarget);
    // message.put("notification", notification);
    // message.put("webpush", webPush);
    // // message.put("data", data);
    // Map<String, Object> request = new HashMap<>();
    // request.put("message", message);
    // System.out.println(request);
    //
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_JSON);
    // headers.setBearerAuth(getAccessToken());
    // HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
    // restTemplate.postForEntity(FCM_URL, entity, String.class);
    // } catch (Exception e) {
    // System.out.println("Erro na notificacao: " + e);
    // }
    // }
    // @Override
    // public void sendNotification(
    // String tokenTarget,
    // String title,
    // String body,
    // String email) {
    // try {
    // String link = "https://speakflowchat.vercel.app/chat?user="
    // + email
    // + "&openAt="
    // + System.currentTimeMillis();
    // Map<String, String> headersWebPush = new HashMap<>();
    //
    // headersWebPush.put("Urgency", "high");
    // headersWebPush.put("TTL", "60");
    //
    // Map<String, Object> webPush = new HashMap<>();
    // webPush.put("headers", headersWebPush);
    // Map<String, String> data = new HashMap<>();
    // data.put("title", title);
    // data.put(body.startsWith("https://") ? "image" : "body", body);
    // data.put("link", link);
    //
    // Map<String, Object> message = new HashMap<>();
    // message.put("token", tokenTarget);
    // message.put("data", data);
    //
    // message.put("webpush", webPush);
    //
    // Map<String, Object> request = new HashMap<>();
    // request.put("message", message);
    //
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_JSON);
    // headers.setBearerAuth(getAccessToken());
    //
    // HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
    //
    // restTemplate.postForEntity(
    // FCM_URL,
    // entity,
    // String.class);
    //
    // } catch (Exception e) {
    // System.out.println(
    // "Erro na notificação: " + e.getMessage());
    // }
    // }
    @Override
    public void sendNotification(
            String tokenTarget,
            String title,
            String body,
            String email) {
        try {
            boolean isImage = body.startsWith("https://")
                    || body.startsWith("http://");

            String link = "https://speakflowchat.vercel.app/chat?user="
                    + email
                    + "&openAt="
                    + System.currentTimeMillis();

            /*
             * Conteúdo padrão da notificação.
             * O Firebase/Chrome fará a exibição automaticamente.
             */
            Map<String, Object> notification = new HashMap<>();

            notification.put("title", title);
            notification.put(
                    "body",
                    isImage
                            ? "Você recebeu uma nova imagem"
                            : body);

            /*
             * Configuração específica para Web Push.
             */
            Map<String, Object> webNotification = new HashMap<>();

            webNotification.put(
                    "icon",
                    "https://speakflowchat.vercel.app/speakflow.png");

            webNotification.put(
                    "badge",
                    "https://speakflowchat.vercel.app/icon-192-round.png");

            if (isImage) {
                webNotification.put("image", body);
            }

            /*
             * Cabeçalhos de prioridade e validade.
             */
            Map<String, String> headersWebPush = new HashMap<>();

            headersWebPush.put("Urgency", "high");
            headersWebPush.put("TTL", "60");

            /*
             * Link aberto ao tocar na notificação.
             */
            Map<String, Object> fcmOptions = new HashMap<>();
            fcmOptions.put("link", link);

            Map<String, Object> webPush = new HashMap<>();

            webPush.put("headers", headersWebPush);
            webPush.put("notification", webNotification);
            webPush.put("fcm_options", fcmOptions);

            /*
             * Mensagem enviada ao Firebase.
             */
            Map<String, Object> message = new HashMap<>();

            message.put("token", tokenTarget);
            message.put("notification", notification);
            message.put("webpush", webPush);

            Map<String, Object> request = new HashMap<>();
            request.put("message", message);

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(getAccessToken());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

            var response = restTemplate.postForEntity(
                    FCM_URL,
                    entity,
                    String.class);

            System.out.println(
                    "FCM enviado: " + response.getStatusCode());

        } catch (Exception e) {
            System.out.println(
                    "Erro na notificação: " + e.getMessage());

            e.printStackTrace();
        }
    }
}
