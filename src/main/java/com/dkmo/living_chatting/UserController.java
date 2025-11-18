package com.dkmo.living_chatting;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/save")
    public ResponseEntity<UserModel> createUser(@RequestBody UsersDto user) {
        return usersService.createUser(user);

    }

    @GetMapping("/findall")
    public List<UserModel> findAllUsers() {
        return usersService.findAllUsers();
    }

    @GetMapping("/find-by-email")
    public ResponseDto findByEmail(@RequestParam(name = "email") String email,
            @RequestHeader(name = "password") String senha,
            HttpServletResponse response) throws IOException {
        return usersService.findByEmail(email, senha, response);
    }

    @GetMapping("/find-users/{email}")
    public String findUsersForEmail(@PathVariable String email) {
        return usersService.findUsersForEmail(email);
    }

    @PostMapping("/allow-notification")
    public String isNotificated(@RequestBody userDtoUpdate update) {
        return usersService.isNotificated(update);
    }

    @GetMapping("/is-notificated")
    public Boolean userIsNotificated(@RequestParam(name = "email") String email) {
        return usersService.userIsNotificated(email);
    }

    @PostMapping("/save-token")
    public void saveToken(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String token = body.get("token");
        usersService.saveFcmToken(email, token);
    }
}
