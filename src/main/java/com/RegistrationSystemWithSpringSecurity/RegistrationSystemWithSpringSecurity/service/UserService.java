package com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.service;

import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.User;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public List<User> users() throws Exception {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw (new Exception());
        }
        return users;
    }


    public User registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String  verifyUser(User user) {
        Authentication authentication =
                manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return  "Logged in";
        }
        throw new RuntimeException("User  not authenticated");

    }
}
