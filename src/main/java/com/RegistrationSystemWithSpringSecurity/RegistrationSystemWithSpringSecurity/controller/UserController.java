package com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.controller;

import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.User;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() throws Exception {
        return userService.users();
    }


    @PostMapping("/register")
  public User registerUser( @RequestBody User user){
        return  userService.registerUser(user);
  }

  @PostMapping("/login")
  public String  login(@RequestBody User user){
        return  userService.verifyUser(user);
  }




}
