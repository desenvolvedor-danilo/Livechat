// package com.dkmo.living_chatting;
//
// import java.io.IOException;
// import java.util.List;
// import java.util.UUID;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cache.annotation.Cacheable;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
// import org.springframework.data.mongodb.core.query.Update;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
//
// import com.dkmo.living_chatting.infra.persistence.UserModel;
// import com.dkmo.living_chatting.infra.persistence.UsersRepository;
//
// import jakarta.servlet.http.HttpServletResponse;
//
// @Service
// public class UsersService {
//
//     @Autowired
//     private UsersRepository usersRepository;
//     @Autowired
//     private MongoTemplate mongoTemplate;
//
//     public ResponseEntity<UserModel> createUser(UsersDto user) {
//         UserModel usuario = usersRepository.findByEmail(user.email());
//         if (usuario == null) {
//             UserModel userModel = new UserModel();
//             userModel.setName(user.name());
//             userModel.setEmail(user.email());
//             userModel.setUsuario(user.usuario());
//             userModel.setSenha(user.senha());
//             userModel.setNotificated(false);
//             userModel.setId(UUID.randomUUID().toString());
//             usersRepository.save(userModel);
//             return ResponseEntity.status(HttpStatus.OK).body(userModel);
//         }
//         return ResponseEntity.status(HttpStatus.CONFLICT).build();
//     }
//
//     public List<UserModel> findAllUsers() {
//         return usersRepository.findAll();
//     }
//
//     @Cacheable("users-by-email")
//     public ResponseDto findByEmail(String email, String senha,
//             HttpServletResponse response) throws IOException {
//         UserModel userModel = usersRepository.findByEmail(email);
//         if (userModel != null && senha.equals(userModel.getSenha())) {
//             Query query = new Query(Criteria.where("email").is(email));
//             Update update = new Update().set("isConnected", true).set("online", true);
//             mongoTemplate.updateFirst(query, update, UserModel.class);
//
//             return ResponseDto.builder().usuario(userModel.getUsuario()).email(userModel.getEmail())
//                     .build();
//         }
//         response.sendError(404);
//         return null;
//
//     }
//
//     // public boolean userIsConnected(String email) {
//     // UserModel userModel = usersRepository.findByEmail(email);
//     // if (userModel.isConnected()) {
//     // return true;
//     // }
//     // return false;
//     // }
//
//     public String findUsersForEmail(String email) {
//         UserModel userModel = usersRepository.findByEmail(email);
//         if (userModel != null) {
//             return userModel.getName();
//         }
//         return null;
//     }
//
//     public void saveFcmToken(String email, String token) {
//         UserModel userModel = usersRepository.findByEmail(email);
//         if (userModel != null) {
//             Query query = new Query(Criteria.where("email").is(email));
//             Update update = new Update().set("fcmToken", token);
//             mongoTemplate.updateFirst(query, update, UserModel.class);
//         }
//
//     }
//
//     public String isNotificated(userDtoUpdate update) {
//
//         UserModel userModel = usersRepository.findByEmail(update.email());
//         if (userModel != null) {
//
//             userModel.setNotificated(update.notificated());
//             // Query query = new Query(Criteria.where("email").is(email));
//             // Update update = new Update().set("notificated", notification);
//             mongoTemplate.save(userModel);
//             return "noticacao ativada";
//         }
//         return "houve um erro";
//
//     }
//
//     @Cacheable("user is notificated")
//     public Boolean userIsNotificated(String email) {
//         UserModel userModel = usersRepository.findByEmail(email);
//         return userModel.isNotificated();
//     }
//   public String setFotoPerfil(MultipartFile file,String email){
//     UserModel userModel = usersRepository.findByEmail(email);
//     if(userModel != null){
//
//     Utils setFotoPerfil = new Utils();
//     String url = setFotoPerfil.setPhotoProfile(file);
//     userModel.setPhotoProfile(url);
//     usersRepository.save(userModel);
//      return url;
//     }
//     return null;
//     }
// //    @Cacheable("photos-profiles")
//     public String getFotoPerfil(String email){
//         UserModel userModel = usersRepository.findByEmail(email);
//         if(userModel!=null){
//             return userModel.getPhotoProfile();
//         }
//         return null;
//     }
// }
