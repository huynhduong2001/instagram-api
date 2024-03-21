package com.insta.instagram.controller;

import com.insta.instagram.dto.UserDto;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.User;
import com.insta.instagram.response.MessageResponse;
import com.insta.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer id) throws UserException{
        User user = userService.findUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username) throws UserException {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/follow/{followUserId}")
    public ResponseEntity<MessageResponse> followUserHandler(@PathVariable Integer followUserId, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        String message = userService.followUser(user.getId(),followUserId);
        MessageResponse res = new MessageResponse(message);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/unfollow/{unfollowUserId}")
    public ResponseEntity<MessageResponse> unfollowUserHandler(@PathVariable Integer unfollowUserId, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        String message = userService.unfollowUser(user.getId(),unfollowUserId);
        MessageResponse res = new MessageResponse(message);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/req")
    public ResponseEntity<User> findUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/m/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds) throws UserException {
        List<User> users = userService.findUserByIds(userIds);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException {
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PutMapping("/account/edit")
    public ResponseEntity<User> updateUserHandler(@RequestHeader("Authorization") String token, @RequestBody User user) throws UserException {
        User reqUser = userService.findUserProfile(token);
        User updatedUser = userService.updateUserDetails(user, reqUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<UserDto>> getTop5PopularUser(){
        List<UserDto> userDto = userService.getTop5PopularUser();
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
