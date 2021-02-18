package com.elekuvanje.elekuvanje.repository;

import com.elekuvanje.elekuvanje.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
