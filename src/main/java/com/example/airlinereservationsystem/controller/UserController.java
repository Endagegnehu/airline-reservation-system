package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.dto.RoleDto;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import com.example.airlinereservationsystem.dto.UserDto;
import com.example.airlinereservationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public void signup(@RequestBody User user) {
        userService.signup(user);
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginDto userLoginDto) {
        boolean loginSuccessful = userService.login(userLoginDto);
    }

    @PostMapping("/admin/add-role")
    public void addRole(@RequestBody RoleDto userRole) {
        System.out.println("inside addrole.");
        userService.addRole(userRole);
    }

    @DeleteMapping("/admin/remove-role")
    public void removeRole(@RequestBody RoleDto userRole) {
        userService.removeRole(userRole);
    }
}
