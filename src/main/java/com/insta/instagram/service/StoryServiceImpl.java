package com.insta.instagram.service;

import com.insta.instagram.dto.UserDto;
import com.insta.instagram.exception.StoryException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Story;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.StoryRepository;
import com.insta.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Story createStory(Story story, Integer userId) throws UserException {
        User user = userService.findUserById(userId);

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setUserImage(user.getImage());

        story.setUser(userDto);
        story.setTimestamps(LocalDateTime.now());
        user.getStories().add(story);
        userRepository.save(user);
        return storyRepository.save(story);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException {
        User user = userService.findUserById(userId);
        List<Story> stories = user.getStories();

        if (stories.size()==0){
            throw new StoryException("this user doesn't have any story");
        }
        return stories;
    }
}
