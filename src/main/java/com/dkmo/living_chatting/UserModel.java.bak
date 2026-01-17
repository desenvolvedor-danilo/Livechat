package com.dkmo.living_chatting;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "usuarios")
public class UserModel {
    @Id
    private String id;
    @Indexed(unique = true)
    private String usuario;
    @Indexed(unique = true)
    private String email;
    private String name;
    private boolean online;
    private String senha;
    private String fcmToken;
    private boolean notificated;
    private String photoProfile;
}
