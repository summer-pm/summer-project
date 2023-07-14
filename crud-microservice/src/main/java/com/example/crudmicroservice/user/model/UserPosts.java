package com.example.crudmicroservice.user.model;

import com.example.crudmicroservice.task.model.SolutionsAttempts;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_posts")
public class UserPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPostsId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-attempts")
    private User user;

    @OneToOne
    @JoinColumn(name = "attempt_id")
    private SolutionsAttempts solutionsAttempts;

    private String commentary;
}

