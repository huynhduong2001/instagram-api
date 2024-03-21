package com.insta.instagram.repository;

import com.insta.instagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.user.id = :userId")
    public List<Post> findPostByUserId(@Param("userId") Integer userId);

    @Query("select p from Post p where p.user.id in :userIds order by p.createAt desc ")
    public List<Post> findAllPostByUserIds(@Param("userIds") List<Integer> userIds);
}
