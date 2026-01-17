package com.dkmo.living_chatting;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesService {


 public String fileUrl(MultipartFile file){
  Recordable saveFiles = new SaveFiles(); 
 return saveFiles.url(file);
  }
}
