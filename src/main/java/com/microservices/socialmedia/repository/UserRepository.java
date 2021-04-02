package com.microservices.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.socialmedia.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
