package com.dkmo.living_chatting.domain.model;
public class User{
private String nome; 
private String email; 
private String senha;
private String usuario;
private FileReference fileReference;
/**
 * @param nome
 * @param email
 * @param senha
 * @param usuario
 */
private User(String nome, String email, String senha, String usuario) {
  this.nome = nome;
  this.email = email;
  this.senha = senha;
  this.usuario = usuario;
  this.fileReference = null;
}
/**
 * @return the nome
 */
public static User create(String nome,String email,String senha,String usuario){
    return new User(nome, email, senha, usuario);
  }
public String nome() {
  return nome;
}
/**
 * @return the email
 */
public String email() {
  return email;
}
/**
 * @return the senha
 */
public String senha() {
  return senha;
}
/**
 * @return the usuario
 */
public String usuario() {
  return usuario;
}
public void definePhotoProfile(FileReference fileReference){
this.fileReference = fileReference;
}
public FileReference getFileReference() {
  return fileReference;
}

}
