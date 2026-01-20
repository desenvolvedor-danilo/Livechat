package com.dkmo.living_chatting.controller;

import java.util.List;

import javax.security.auth.login.CredentialNotFoundException;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.living_chatting.application.usecases.CreateUserInteractor;
import com.dkmo.living_chatting.application.usecases.LoadAllUsersUseCase;
import com.dkmo.living_chatting.application.usecases.LoginPolicyInteractor;
import com.dkmo.living_chatting.controller.DTOs.LoginRequestDTO;
import com.dkmo.living_chatting.controller.DTOs.UserRequestDTO;
import com.dkmo.living_chatting.controller.DTOs.UserResponseDTO;
import com.dkmo.living_chatting.controller.adapters.UserAdapter;
import com.dkmo.living_chatting.domain.model.User;

@RestController
@RequestMapping("/users")

public class UserController {
  private final CreateUserInteractor createUserInteractor;
  private final LoginPolicyInteractor loginUserInteractor;
  private final UserAdapter userDTOMapper; 
  private final LoadAllUsersUseCase loadAllUsersUseCase;
  
  public UserController(CreateUserInteractor createUserInteractor,UserAdapter userDTOMapper,LoginPolicyInteractor loginPolicyInteractor,LoadAllUsersUseCase loadAllUsersUseCase) {
    this.createUserInteractor = createUserInteractor;
    this.userDTOMapper = userDTOMapper;
    this.loginUserInteractor = loginPolicyInteractor;
    this.loadAllUsersUseCase = loadAllUsersUseCase;
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
}  
