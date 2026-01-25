package com.dkmo.living_chatting.domain.model;

public final class FileReference {
private final String url;
  public FileReference(String url){
    this.url = url;
  }
  /**
   * @return the value
   */
  public String url() {
    return url;
  }

}
