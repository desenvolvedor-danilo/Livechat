package com.dkmo.living_chatting.infrastructure.gateways;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import com.dkmo.living_chatting.application.gateway.LoadFileGateway;
import com.dkmo.living_chatting.infrastructure.exceptions.FileStorageException;

public class LoadFile implements LoadFileGateway{
private final String URI = "uploads/photos-profiles/";
private final String BASE_URL = "http://localhost:8080/photos-profiles/";

  @Override
  public String loadFile(byte[] file,String originalFileName) {
    try{
   Path path = Paths.get(URI+originalFileName);
    Files.write(path,file);
      return BASE_URL+originalFileName;
    }catch(IOException io){
    throw new FileStorageException();
    }
}
}
