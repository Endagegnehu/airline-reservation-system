package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.domain.UserRole;
import com.example.airlinereservationsystem.util.constant.Roles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    public void addUser(User user);
//    public void addUserRole(UserRole roles);
    public Optional<User> findUserByUsername(String firstName);

    public UserDetails getUserDetails(String firstName);
}
