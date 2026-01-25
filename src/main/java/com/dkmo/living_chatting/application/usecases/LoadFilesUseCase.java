package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.EditUserGateway;
import com.dkmo.living_chatting.application.gateway.LoadFileGateway;
import com.dkmo.living_chatting.application.gateway.LoginPolicyGateway;
import com.dkmo.living_chatting.application.inputs.PhotoProfileInput;
import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;

public class LoadFilesUseCase {
private final LoadFileGateway loadFileGateway;
private final LoginPolicyGateway loadUserGateway;
private final EditUserGateway editUserGateway;
  public LoadFilesUseCase(LoadFileGateway loadFileGateway,LoginPolicyGateway loadUserGateway,EditUserGateway editUserGateway){
    this.loadFileGateway = loadFileGateway;
    this.loadUserGateway = loadUserGateway;
    this.editUserGateway = editUserGateway;
  }
  public FileReference execute(PhotoProfileInput file){
     User user = loadUserGateway.findByEmail(file.getEmail());
     String arq = loadFileGateway.loadFile(file.getFile(),file.getOriginalFileName());
     editUserGateway.editPhotoProfile(user,arq);
    FileReference fileReference = new FileReference(arq);
    user.definePhotoProfile(fileReference);
    return user.getFileReference();
    }
}
