package com.dkmo.living_chatting.controller;

import java.io.IOException;
// import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.living_chatting.application.gateway.GenerateCookieGateway;
import com.dkmo.living_chatting.application.inputs.ImageInput;
import com.dkmo.living_chatting.application.inputs.InputCreateUser;
import com.dkmo.living_chatting.application.usecases.CreateUserInteractor;
import com.dkmo.living_chatting.application.usecases.FcmTokenUseCase;
import com.dkmo.living_chatting.application.usecases.GenerateTokenUseCase;
import com.dkmo.living_chatting.application.usecases.GetPhotoProfileInput;
import com.dkmo.living_chatting.application.usecases.GetPhotoProfileUseCase;
import com.dkmo.living_chatting.application.usecases.LoadAllUsersUseCase;
import com.dkmo.living_chatting.application.usecases.LoadFilesUseCase;
import com.dkmo.living_chatting.application.usecases.LoadUserUseCase;
import com.dkmo.living_chatting.controller.DTOs.GetNameUserDto;
import com.dkmo.living_chatting.controller.DTOs.LoginRequestDTO;
import com.dkmo.living_chatting.controller.DTOs.LoginResponseDto;
import com.dkmo.living_chatting.controller.DTOs.PhotoProfileDto;
import com.dkmo.living_chatting.controller.DTOs.UpdateUserDto;
import com.dkmo.living_chatting.controller.DTOs.UserProfileResponseDto;
import com.dkmo.living_chatting.controller.DTOs.UserRequestDTO;
import com.dkmo.living_chatting.controller.DTOs.UserResponseDTO;
import com.dkmo.living_chatting.controller.adapters.UserAdapter;
import com.dkmo.living_chatting.controller.adapters.UserMapper;
import com.dkmo.living_chatting.domain.model.AbstractAuthorization;
import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.domain.model.UsersReference;
import com.dkmo.living_chatting.infrastructure.adapters.AuthenticationGateway;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

//@CrossOrigin(origins =  "*")
@RestController
@RequestMapping("/users")

public class UserController {
  private final GenerateCookieGateway generateCookieGateway;
  private final CreateUserInteractor createUserInteractor;
  private final UserAdapter userDTOMapper;
  private final LoadAllUsersUseCase loadAllUsersUseCase;
  private final LoadFilesUseCase loadFilesUseCase;
  private final GetPhotoProfileUseCase getPhotoProfileUseCase;
  private final LoadUserUseCase loadUserUseCase;
  private final GenerateTokenUseCase generateTokenUseCase;
  @Autowired
  private AuthenticationGateway authenticationManager;
  private final FcmTokenUseCase fcmTokenUseCase;
  @Autowired
  private UserMapper userMapper;

  public UserController(CreateUserInteractor createUserInteractor, UserAdapter userDTOMapper,
      LoadAllUsersUseCase loadAllUsersUseCase, LoadFilesUseCase loadFilesUseCase,
      GetPhotoProfileUseCase getPhotoProfileUseCase, LoadUserUseCase loadUserUseCase, FcmTokenUseCase fcmTokenUseCase,
      GenerateTokenUseCase generateTokenUseCase, GenerateCookieGateway generateCookieGateway) {
    this.createUserInteractor = createUserInteractor;
    this.userDTOMapper = userDTOMapper;
    this.loadAllUsersUseCase = loadAllUsersUseCase;
    this.loadFilesUseCase = loadFilesUseCase;
    this.getPhotoProfileUseCase = getPhotoProfileUseCase;
    this.loadUserUseCase = loadUserUseCase;
    this.fcmTokenUseCase = fcmTokenUseCase;
    this.generateTokenUseCase = generateTokenUseCase;
    this.generateCookieGateway = generateCookieGateway;
  }

  @PostMapping("/create")
  public UserResponseDTO create(@RequestBody UserRequestDTO request) {
    InputCreateUser user = new InputCreateUser(request.nome(), request.email(), request.password(), request.username());
    User userInteractor = createUserInteractor.createUser(user);
    return userDTOMapper.toResponse(userInteractor);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDTO request) {
    System.out.println("chamou o endpoint");
    Authentication authentication = authenticationManager.authentication(request.email(), request.password());
    AbstractAuthorization abstractAuthorization = generateTokenUseCase.execute(request.email());
    UserEntity userEntity = (UserEntity) authentication.getPrincipal();
    LoginResponseDto loginResponseDto = new LoginResponseDto(userEntity.getName());
    generateCookieGateway.write("token", abstractAuthorization.token());
    generateCookieGateway.write("refresh-token", abstractAuthorization.refreshToken());
    return ResponseEntity.ok(loginResponseDto);
  }

  @GetMapping("findall")
  public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
    try {
      List<User> user = loadAllUsersUseCase.execute();
      return ResponseEntity.ok().body(userDTOMapper.listToResponse(user));
    } catch (Exception e) {
      return ResponseEntity.noContent().build();
    }
  }

  @PostMapping("/photo-profile")
  public UserProfileResponseDto updatePhotoProfile(@RequestParam(name = "file") MultipartFile multipartFile,
      @RequestParam(name = "email") String email) throws IOException {
    ImageInput profileInput = new ImageInput(multipartFile.getBytes(), email, multipartFile.getOriginalFilename(),
        "uploads/photos-profiles/");
    FileReference fileReference = loadFilesUseCase.execute(profileInput);
    return userMapper.toUserProfileResponseDto(fileReference);
  }

  @GetMapping("/get-photo-profile")
  public PhotoProfileDto getPhotoProfile(@RequestParam(name = "email") String email) {
    GetPhotoProfileInput getPhotoProfileInput = new GetPhotoProfileInput(email);
    FileReference fileReference = getPhotoProfileUseCase.getPhotoProfile(getPhotoProfileInput);
    return new PhotoProfileDto(fileReference.url());
  }

  @GetMapping("/find-users")
  public GetNameUserDto getNameUserDto(@RequestParam("email") String email) {
    UsersReference usersReference = loadUserUseCase.execute(email);
    return new GetNameUserDto(usersReference.name(), usersReference.urlPhotoProfile());
  }

  @PostMapping("/save-token")
  public void setToken(@RequestBody UpdateUserDto updateUserDto) {
    fcmTokenUseCase.execute(updateUserDto.email(), updateUserDto.token());
  }
}
