package com.dkmo.living_chatting.application.inputs;

public class PhotoProfileInput {
private byte [] file;
private String email;
private String originalFileName;
/**
 * @param file
 * @param idUser
 */
public PhotoProfileInput(byte[] file, String email,String originalFileName) {
  this.file = file;
  this.email = email;
  this.originalFileName = originalFileName;

}
/**
 * @return the file
 */
public byte[] getFile() {
  return file;
}
/**
 * @return the idUser
 */
public String getEmail() {
  return email;
}
public String getOriginalFileName(){
    return originalFileName;
  }

}

