package com.dkmo.living_chatting.infrastructure.persistence;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Document(collection = "RefreshToken" )
public class RefreshTokenEntity {
@Id
private String id;
private String idUser;
private String hashToken;
private Instant expiresAt;
private Instant createdAt;  
}
