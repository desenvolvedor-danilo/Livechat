package com.dkmo.living_chatting.infrastructure.exceptions;

public class FileStorageException extends RuntimeException {
  public FileStorageException(){
    super("Error to the storaged");
  }

}

