package com.insta.instagram.service;

import com.insta.instagram.dto.UserDto;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.User;

import java.util.List;

public interface UserService{
    public User registerUser(User user) throws UserException;
    public User findUserById(Integer userId) throws UserException;
    public User findUserProfile(String token) throws UserException;
    public User findUserByUsername(String username) throws UserException;
    public String followUser(Integer reqUserId, Integer followUserId) throws UserException;
    public String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException;
    public List<User> findUserByIds(List<Integer> userIds) throws UserException;
    public List<User> searchUser(String query) throws UserException;
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException;
    public List<UserDto> getTop5PopularUser();

}
