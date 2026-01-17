package com.dkmo.living_chatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FilesController {
  @Autowired
  private FilesService filesService;
  // @PostMapping("/save")
  // public String saveFiles(@RequestParam(name = "File") MultipartFile file){
  //   return filesService.fileUrl(file);
  // }
}
