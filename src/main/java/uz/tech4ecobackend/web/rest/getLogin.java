package uz.tech4ecobackend.web.rest;

import uz.tech4ecobackend.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class getLogin {
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getLogin")
    public ResponseEntity getUserName(){
        Optional<String> optional = SecurityUtils.getCurrentUserName();
        return ResponseEntity.ok(optional);
    }
}
