package com.dkmo.living_chatting;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class UserModel {
    @Id
    private String id;
    @Indexed(unique = true)
    private String usuario;
    @Indexed(unique = true)
    private String email;

    private String senha;

    private String fcmToken;

    private boolean notificated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public boolean isNotificated() {
        return notificated;
    }

    public void setNotificated(boolean notificated) {
        this.notificated = notificated;
    }

}
