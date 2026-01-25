package com.dkmo.living_chatting.controller.adapters;

import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.controller.DTOs.UserProfileResponseDto;
import com.dkmo.living_chatting.domain.model.FileReference;
@Component
public class UserMapper {
   public UserProfileResponseDto toUserProfileResponseDto(FileReference fileReference){
    return new UserProfileResponseDto(fileReference.url());
  }  
}
