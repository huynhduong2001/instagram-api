package com.insta.instagram.service;

import com.insta.instagram.exception.StoryException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Story;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, Integer userId) throws UserException;
    public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException;

}
