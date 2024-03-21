package com.insta.instagram.controller;

import com.insta.instagram.exception.CommentException;
import com.insta.instagram.exception.PostException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Comment;
import com.insta.instagram.model.User;
import com.insta.instagram.service.CommentService;
import com.insta.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createCommentHandler(@RequestBody Comment comment,@PathVariable Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        Comment createdComment = commentService.createComment(comment,postId, user.getId());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> findCommentByIdHandler(@PathVariable Integer commentId, @RequestHeader("Authorization") String token) throws UserException, PostException, CommentException {
        User user = userService.findUserProfile(token);
        Comment comment = commentService.findCommentById(commentId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comment> likeCommentHandler(@PathVariable Integer commentId,@RequestHeader("Authorization") String token) throws UserException, CommentException {
        User user = userService.findUserProfile(token);
        Comment comment = commentService.likeComment(commentId, user.getId());
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/unlike/{commentId}")
    public ResponseEntity<Comment> unlikeCommentHandler(@PathVariable Integer commentId,@RequestHeader("Authorization") String token) throws UserException, CommentException {
        User user = userService.findUserProfile(token);
        Comment comment = commentService.unlikeComment(commentId, user.getId());
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
