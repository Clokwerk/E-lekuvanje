package com.elekuvanje.elekuvanje.service.impl;

import com.elekuvanje.elekuvanje.model.Role;
import com.elekuvanje.elekuvanje.model.User;
import com.elekuvanje.elekuvanje.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserService {
    @Override
    public User register(String username, String password, String repeatPassword, String firstName, String lastName, String EMBG, Role role) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
