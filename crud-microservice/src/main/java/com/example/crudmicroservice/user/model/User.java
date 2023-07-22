package com.example.crudmicroservice.user.model;

import com.example.crudmicroservice.task.model.SolutionsAttempts;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String userImage;
    private LocalDateTime dateOfBirth;
    private LocalDateTime dateOfRegistration;
    private LocalDateTime dateOfEdit;
    private LocalDateTime dateOfLastActivity;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-attempts")
    @ToStringExclude
    private List<SolutionsAttempts> solutionsAttemptsList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToStringExclude
    private List<UserPosts> userPostsList;
}
