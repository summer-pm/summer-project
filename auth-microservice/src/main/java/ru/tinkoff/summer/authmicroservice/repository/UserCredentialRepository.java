package ru.tinkoff.summer.authmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.summer.authmicroservice.entity.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
    Optional<UserCredential> findByEmail(String email);
}
