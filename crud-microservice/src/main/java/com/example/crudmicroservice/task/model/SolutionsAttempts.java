package com.example.crudmicroservice.task.model;

import com.example.crudmicroservice.user.model.User;
import com.example.crudmicroservice.user.model.UserPosts;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "solutions_attempts")
public class SolutionsAttempts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    @OneToOne
    @Transient
    private UserPosts userPosts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-attempts")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference("task-attempts")
    private Task task;

    private String code;
    private String language;
    private String status;
    private Long executionTime;
    private Long solutionsVolume;

}
