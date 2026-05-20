package com.dkmo.living_chatting.application.gateway;

public interface NotificationGateway {

    void sendNotification(String tokenTarget, String title, String body,String email);

}

