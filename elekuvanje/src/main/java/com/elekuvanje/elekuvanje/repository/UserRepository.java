package com.elekuvanje.elekuvanje.repository;

import com.elekuvanje.elekuvanje.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
Optional<User> findByUsername(String s);
Optional<User> findById(Long Id);

    Optional<User>findByUsernameAndPassword(String username, String password);
}
