package com.dkmo.living_chatting.domain.model;

public class UsersReference {
private String name;
private String urlPhotoProfile;

/**
 * @return the name
 */
public String name() {
  return name;
}
public String urlPhotoProfile(){
    return urlPhotoProfile != null ? urlPhotoProfile : null;
  }

/**
 * @param name
 */
public UsersReference(String name,String urlPhotoProfile) {
  this.name = name;
  this.urlPhotoProfile = urlPhotoProfile;
}    
  
}
