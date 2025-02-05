package com.project.uber.uberApp.repositories;

import com.project.uber.uberApp.entities.User;
import com.project.uber.uberApp.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByEmail(String email);
    Optional<User> findByEmail(String email);
}
