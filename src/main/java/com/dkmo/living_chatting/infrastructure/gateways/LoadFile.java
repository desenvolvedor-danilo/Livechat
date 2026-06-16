package com.dkmo.living_chatting.infrastructure.gateways;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.dkmo.living_chatting.application.gateway.LoadFileGateway;
import com.dkmo.living_chatting.infrastructure.exceptions.FileStorageException;

public class LoadFile implements LoadFileGateway {
  private final String BASE_URL = "https://speakflow.ddns.net/";

  @Override
  // @Cacheable
  public String loadFile(byte[] file, String originalFileName, String folder) {

    try {
      Path path = Paths.get(folder + originalFileName);
      Files.write(path, file);
      return BASE_URL + folder + originalFileName;
    } catch (IOException io) {
      throw new FileStorageException();
    }
  }
}
