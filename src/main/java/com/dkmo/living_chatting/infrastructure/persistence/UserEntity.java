package com.dkmo.living_chatting.infrastructure.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;

import lombok.Data;
@Data
@Document(collection = "usuarios")
public class UserEntity implements UserDetails {
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

    public UserEntity(String name,String email, String senha, String usuario) {
      this.name = name;
      this.email = email;
      this.senha = senha;
      this.usuario = usuario;
    }

    public User toDomain(){
       User userCreated =User.create(name, email,senha, usuario);
      FileReference fileReference = new FileReference(photoProfile!=null?photoProfile:null);
     userCreated.definePhotoProfile(fileReference);
    return userCreated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }    
}
