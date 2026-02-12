package com.dkmo.living_chatting.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.living_chatting.application.inputs.ImageInput;
import com.dkmo.living_chatting.application.usecases.ImagesUseCases;
import com.dkmo.living_chatting.controller.DTOs.UserProfileResponseDto;
import com.dkmo.living_chatting.controller.adapters.UserMapper;
import com.dkmo.living_chatting.domain.model.FileReference;

@RestController
@RequestMapping("/files")
public class UploadsFilesController {

@Autowired
private UserMapper userMapper;
private final ImagesUseCases imagesUseCases;

/**
 * @param imagesUseCases
 */
public UploadsFilesController(ImagesUseCases imagesUseCases) {
  this.imagesUseCases = imagesUseCases;
}

@PostMapping("/save") 
public UserProfileResponseDto messageImageUpload(@RequestParam(name = "file")MultipartFile multipartFile) throws IOException{ 
   ImageInput profileInput = new ImageInput(multipartFile.getBytes(),null, multipartFile.getOriginalFilename(),"imagens/");
   FileReference fileReference = imagesUseCases.execute(profileInput);
    return userMapper.toUserProfileResponseDto(fileReference);
  }
}
