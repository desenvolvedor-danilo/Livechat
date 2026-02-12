package com.dkmo.living_chatting.application.inputs;

public class ImageInput {
private byte [] file;
private String email;
private String originalFileName;
private String folder;
/**
 * @param file
 * @param idUser
 */
public ImageInput(byte[] file, String email,String originalFileName,String folder) {
  this.file = file;
  this.email = email;
  this.originalFileName = originalFileName;
  this.folder = folder;
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
/**
 * @return the folder
 */
public String getFolder() {
  return folder;
}


}

