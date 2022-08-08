package uz.tech4ecobackend.web.rest;

import uz.tech4ecobackend.entity.User;
import uz.tech4ecobackend.security.SecurityUtils;
import uz.tech4ecobackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/account")
    public ResponseEntity<?> getAccount(){
        String login = SecurityUtils.getCurrentUserName().get();
        User user = userService.findByUser(login);
        return ResponseEntity.ok(user);
    }
}
