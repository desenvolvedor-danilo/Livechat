package com.dkmo.living_chatting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class SaveFiles implements Recordable {

  @Override
  public String url(MultipartFile file){
    String path = "imagens/";
    try{
    if(file!=null){
    byte [] image = file.getBytes();
    Path fullPath = Paths.get(path+file.getOriginalFilename());
      Files.write(fullPath, image);
      return "http://localhost:8080/imagens/"+file.getOriginalFilename();
      }
    }catch(IOException ie){

    }
    return null;
  }   
}
