package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.LoadFileGateway;
import com.dkmo.living_chatting.application.inputs.ImageInput;
import com.dkmo.living_chatting.domain.model.FileReference;

public class ImagesUseCases {
private final LoadFileGateway loadFileGateway;

/**
 * @param imagesGateway
 */
public ImagesUseCases(LoadFileGateway loadFileGateway) {
  this.loadFileGateway = loadFileGateway;
}
  public FileReference execute(ImageInput imageInput){
    String url = loadFileGateway.loadFile(imageInput.getFile(), imageInput.getOriginalFileName(), imageInput.getFolder());
    return new FileReference(url);
  }
}
