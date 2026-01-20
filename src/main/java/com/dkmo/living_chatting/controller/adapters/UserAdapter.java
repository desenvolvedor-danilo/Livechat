package com.dkmo.living_chatting.controller.adapters;

import java.util.ArrayList;
import java.util.List;

import com.dkmo.living_chatting.controller.DTOs.UserRequestDTO;
import com.dkmo.living_chatting.controller.DTOs.UserResponseDTO;
import com.dkmo.living_chatting.domain.model.User;

public class UserAdapter {
public UserResponseDTO toResponse(User user){
    return new UserResponseDTO(user.email(),user.nome());
  }

public List<UserResponseDTO> listToResponse(List<User> user){
    List<UserResponseDTO> createUserResponse = new ArrayList<>();
    user.forEach(usuario->{
      UserResponseDTO response = new UserResponseDTO(usuario.email(), usuario.nome());
      createUserResponse.add(response);
    });
    return createUserResponse;
  }

public User toUser(UserRequestDTO request){
    return new User(request.nome(),request.email(),request.password(),request.username());
  }
}
