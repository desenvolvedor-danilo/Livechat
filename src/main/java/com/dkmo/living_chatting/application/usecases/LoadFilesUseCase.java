package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.EditUserGateway;
import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.application.gateway.LoadFileGateway;
import com.dkmo.living_chatting.application.inputs.ImageInput;
import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;

public class LoadFilesUseCase {
  private final LoadFileGateway loadFileGateway;
  private final FindUserGateway loadUserGateway;
  private final EditUserGateway editUserGateway;

  public LoadFilesUseCase(LoadFileGateway loadFileGateway, FindUserGateway loadUserGateway,
      EditUserGateway editUserGateway) {
    this.loadFileGateway = loadFileGateway;
    this.loadUserGateway = loadUserGateway;
    this.editUserGateway = editUserGateway;
  }

  public FileReference execute(ImageInput file) {
    User user = loadUserGateway.findByEmail(file.getEmail());
    String arq = loadFileGateway.loadFile(file.getFile(), file.getOriginalFileName(), file.getFolder());

    FileReference fileReference = new FileReference(arq);
    user.definePhotoProfile(fileReference);
    editUserGateway.editPhotoProfile(user, arq);

    return user.getFileReference();
  }
}
