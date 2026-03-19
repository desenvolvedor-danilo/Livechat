package com.dkmo.living_chatting.controller.DTOs;

public class PhotoProfileDto {
  private final String url;
  public PhotoProfileDto(String url){
   if(url!=null){
      this.url = url;
    }else{
    this.url = null;
    }
  }
  /**
   * @return the fileReference
   */
  public String getUrl() {
    return url != null ? url : null;
  }

}
