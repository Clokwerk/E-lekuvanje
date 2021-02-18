package com.elekuvanje.elekuvanje.service;

import com.elekuvanje.elekuvanje.model.Role;
import com.elekuvanje.elekuvanje.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username,String password,String repeatPassword,String firstName,String lastName, String EMBG, Role role);
}
