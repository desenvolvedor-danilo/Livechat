package com.dkmo.living_chatting;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
 public String setPhotoProfile(MultipartFile file){
    String path = "uploads/photos-profiles/";
    try {
    if(file!=null){
        byte [] imagem = file.getBytes();
        Path fullPath = Paths.get(path+file.getOriginalFilename());
        Files.write(fullPath, imagem);
        return "http://localhost:8080/photos-profiles/"+file.getOriginalFilename();
      }  
    } catch (Exception e) {
      System.out.println("Ocorreu um erro"+e.getCause());
    }
    return null;
  }   
}
