package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.dto.RoleDto;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import com.example.airlinereservationsystem.dto.UserDto;
import com.example.airlinereservationsystem.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserResponseDto> getAllUsers();

    ResponseEntity<?> signup(User user);

    Optional<User> findUserByUsername(String firstName);

    Optional<User> findUserByID(long id);

    UserDto addRole(RoleDto role);

    UserDto removeRole(RoleDto role);

    UserDetails getUserDetails(String firstName);
}
