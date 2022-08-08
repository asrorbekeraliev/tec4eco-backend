package uz.tech4ecobackend.service;

import uz.tech4ecobackend.entity.User;
import uz.tech4ecobackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save (User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByLogin(String login){
        return userRepository.existsByLogin(login);
    }

    public User findByUser(String login){
        User user = userRepository.findByLogin(login);
        return user;
    }



}
