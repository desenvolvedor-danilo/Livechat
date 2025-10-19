package com.dkmo.living_chatting;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public UserModel createUser(UsersDto user) {
        String id = UUID.randomUUID().toString();
        System.out.println(id);
        UserModel userModel = new UserModel();
        userModel.setEmail(user.email());
        userModel.setUsuario(user.usuario());
        userModel.setSenha(user.senha());
        userModel.setNotificated(false);
        userModel.setId(id);
        return usersRepository.save(userModel);

    }

    public List<UserModel> findAllUsers() {
        return usersRepository.findAll();
    }

    @Cacheable("users-by-email")
    public ResponseDto findByEmail(String email, String senha,
            HttpServletResponse response) throws IOException {
        UserModel userModel = usersRepository.findByEmail(email);
        if (userModel != null && senha.equals(userModel.getSenha())) {
            Query query = new Query(Criteria.where("email").is(email));
            Update update = new Update().set("isConnected", true);
            mongoTemplate.updateFirst(query, update, UserModel.class);

            return ResponseDto.builder().usuario(userModel.getUsuario()).email(userModel.getEmail())
                    .build();
        }
        response.sendError(404);
        return null;

    }

    // public boolean userIsConnected(String email) {
    // UserModel userModel = usersRepository.findByEmail(email);
    // if (userModel.isConnected()) {
    // return true;
    // }
    // return false;
    // }

    public String findUsersForEmail(String email) {
        UserModel userModel = usersRepository.findByEmail(email);
        if (userModel != null) {
            return userModel.getUsuario();
        }
        return null;
    }

    public void saveFcmToken(String email, String token) {
        UserModel userModel = usersRepository.findByEmail(email);
        if (userModel != null) {
            Query query = new Query(Criteria.where("email").is(email));
            Update update = new Update().set("fcmToken", token);
            mongoTemplate.updateFirst(query, update, UserModel.class);
        }

    }

    public String isNotificated(userDtoUpdate update) {

        UserModel userModel = usersRepository.findByEmail(update.email());
        if (userModel != null) {

            userModel.setNotificated(update.notificated());
            // Query query = new Query(Criteria.where("email").is(email));
            // Update update = new Update().set("notificated", notification);
            mongoTemplate.save(userModel);
            return "noticacao ativada";
        }
        return "houve um erro";

    }

    @Cacheable("user is notificated")
    public Boolean userIsNotificated(String email) {
        UserModel userModel = usersRepository.findByEmail(email);
        return userModel.isNotificated();
    }
}
