package com.example.crudmicroservice.repository;

import com.example.crudmicroservice.model.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostsRepository extends JpaRepository<UserPosts, Long> {
}
