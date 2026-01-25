package com.dkmo.living_chatting.controller.DTOs;

public class PhotoProfileDto {
  private final String url;
  public PhotoProfileDto(String url){
    this.url = url;
  }
  /**
   * @return the fileReference
   */
  public String getUrl() {
    return url;
  }

}
