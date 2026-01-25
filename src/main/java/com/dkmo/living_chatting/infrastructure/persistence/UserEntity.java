package com.dkmo.living_chatting.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dkmo.living_chatting.domain.model.User;

import lombok.Data;
@Data
@Document(collection = "usuarios")
public class UserEntity {
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
    /**
     * @param email
     * @param senha
     */
    public UserEntity(String name,String email, String senha, String usuario) {
      this.name = name;
      this.email = email;
      this.senha = senha;
      this.usuario = usuario;
    }
    public User toDomain(){
        return User.create(name, email,null, usuario);
    }
    
}
