package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.dto.RoleDto;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import com.example.airlinereservationsystem.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    void signup(User user);

    Optional<User> findUserByUsername(String firstName);

    boolean login(UserLoginDto userLoginDto);

    UserDto addRole(RoleDto role);

    UserDto removeRole(RoleDto role);

    UserDetails getUserDetails(String firstName);

}
