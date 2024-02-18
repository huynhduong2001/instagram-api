package com.insta.instagram.controller;

import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.UserRepository;
import com.insta.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException {
        User createdUser = userService.registerUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.OK);
    }

    @GetMapping("/signin")
    public ResponseEntity<User> signinHandler(Authentication auth) throws BadCredentialsException{
        System.out.println("gmail "+auth.getName());
        Optional<User> opt = userRepository.findByEmail(auth.getName());
        if (opt.isPresent()){
            return new ResponseEntity<>(opt.get(),HttpStatus.ACCEPTED);
        }
        throw new BadCredentialsException("invalid username or password");
    }
}
