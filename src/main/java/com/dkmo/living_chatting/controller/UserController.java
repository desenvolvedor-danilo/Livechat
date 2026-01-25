package com.dkmo.living_chatting.controller;

import java.io.IOException;
// import java.security.Principal;
import java.util.List;

import javax.security.auth.login.CredentialNotFoundException;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.living_chatting.application.inputs.PhotoProfileInput;
import com.dkmo.living_chatting.application.usecases.CreateUserInteractor;
import com.dkmo.living_chatting.application.usecases.GetPhotoProfileInput;
import com.dkmo.living_chatting.application.usecases.GetPhotoProfileUseCase;
import com.dkmo.living_chatting.application.usecases.LoadAllUsersUseCase;
import com.dkmo.living_chatting.application.usecases.LoadFilesUseCase;
import com.dkmo.living_chatting.application.usecases.LoginPolicyInteractor;
import com.dkmo.living_chatting.controller.DTOs.LoginRequestDTO;
import com.dkmo.living_chatting.controller.DTOs.PhotoProfileDto;
import com.dkmo.living_chatting.controller.DTOs.UserRequestDTO;
import com.dkmo.living_chatting.controller.DTOs.UserProfileResponseDto;

import com.dkmo.living_chatting.controller.DTOs.UserResponseDTO;
import com.dkmo.living_chatting.controller.adapters.UserAdapter;
import com.dkmo.living_chatting.controller.adapters.UserMapper;
import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;

@RestController
@RequestMapping("/users")

public class UserController {
  private final CreateUserInteractor createUserInteractor;
  private final LoginPolicyInteractor loginUserInteractor;
  private final UserAdapter userDTOMapper; 
  private final LoadAllUsersUseCase loadAllUsersUseCase;
  private final LoadFilesUseCase loadFilesUseCase;
  private final GetPhotoProfileUseCase getPhotoProfileUseCase;
  @Autowired
  private UserMapper userMapper;
  public UserController(CreateUserInteractor createUserInteractor,UserAdapter userDTOMapper,LoginPolicyInteractor loginPolicyInteractor,LoadAllUsersUseCase loadAllUsersUseCase,LoadFilesUseCase
  loadFilesUseCase,GetPhotoProfileUseCase getPhotoProfileUseCase) {
    this.createUserInteractor = createUserInteractor;
    this.userDTOMapper = userDTOMapper;
    this.loginUserInteractor = loginPolicyInteractor;
    this.loadAllUsersUseCase = loadAllUsersUseCase;
    this.loadFilesUseCase = loadFilesUseCase;
    this.getPhotoProfileUseCase = getPhotoProfileUseCase;
  }
  @PostMapping("/create")
  public UserResponseDTO  create(@RequestBody UserRequestDTO request){
    User user = userDTOMapper.toUser(request);
    User userInteractor = createUserInteractor.createUser(user);
    return userDTOMapper.toResponse(userInteractor); 
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO request) throws InvalidCredentialsException,CredentialNotFoundException{
  try{
   User user = loginUserInteractor.execute(request.email(), request.password());
      return ResponseEntity.ok(userDTOMapper.toResponse(user));
    }catch(Exception e){
     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } 
  }

  @GetMapping("findall")
  public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
    try {     
  List<User> user = loadAllUsersUseCase.execute();
   return ResponseEntity.ok().body(userDTOMapper.listToResponse(user));
    } catch (Exception e) {
    return ResponseEntity.noContent().build();
    }    
  }
  @PostMapping("/photo-profile") 
public UserProfileResponseDto updatePhotoProfile(@RequestParam(name = "file")MultipartFile multipartFile,@RequestParam(name = "email")String email)throws IOException{ 
   PhotoProfileInput profileInput = new PhotoProfileInput(multipartFile.getBytes(),email, multipartFile.getOriginalFilename());
   FileReference fileReference = loadFilesUseCase.execute(profileInput);
    System.out.println(fileReference.url());
    return userMapper.toUserProfileResponseDto(fileReference);
  }

@GetMapping("/get-photo-profile")
public PhotoProfileDto getPhotoProfile(@RequestParam(name = "email") String email){
GetPhotoProfileInput getPhotoProfileInput = new GetPhotoProfileInput(email);
 FileReference fileReference =  getPhotoProfileUseCase.getPhotoProfile(getPhotoProfileInput);
    return new PhotoProfileDto(fileReference.url()); 
  } 
 }  
