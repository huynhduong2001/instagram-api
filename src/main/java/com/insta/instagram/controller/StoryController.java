package com.insta.instagram.controller;

import com.insta.instagram.exception.StoryException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Story;
import com.insta.instagram.model.User;
import com.insta.instagram.service.StoryService;
import com.insta.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoryService storyService;

    @PostMapping("/create")
    public ResponseEntity<Story> createStoryHandler(@RequestBody Story story, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        Story createdStory = storyService.createStory(story, user.getId());
        return new ResponseEntity<>(createdStory, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Story>> findAllStoryByUserId(@PathVariable Integer userId) throws UserException, StoryException {
        User user = userService.findUserById(userId);
        List<Story> stories = storyService.findStoryByUserId(user.getId());
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
