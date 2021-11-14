package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.dto.RoleDto;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import com.example.airlinereservationsystem.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    void signup(User user);

    boolean login(UserLoginDto userLoginDto);

    UserDto addRole(RoleDto role);

    UserDto removeRole(RoleDto role);
}
