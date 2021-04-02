package com.microservices.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.socialmedia.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
