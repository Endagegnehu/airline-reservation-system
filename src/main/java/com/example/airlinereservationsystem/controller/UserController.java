package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.domain.UserRole;
import com.example.airlinereservationsystem.dto.RoleDto;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import com.example.airlinereservationsystem.dto.UserDto;
import com.example.airlinereservationsystem.dto.UserRegistrationResponse;
import com.example.airlinereservationsystem.service.UserService;
import com.example.airlinereservationsystem.util.JwtUtil;
import com.example.airlinereservationsystem.util.constant.Roles;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;


@RestController
@RequestMapping
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    JwtUtil jwtUtil;


    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setRoleName(Roles.ROLE_USER);
        userRoles.add(userRole);
        User user = modelMapper.map(userDto,User.class);
        user.setRole(userRoles);
        userService.signup(user);
        final Optional<User> currentUser = userService.findUserByUsername(user.getUsername());
        final UserDetails userDetails = userService.getUserDetails(user.getUsername());
        final String jwt  = jwtUtil.generateToken(userDetails);
        logger.info("JWT: " + jwt);
        return ResponseEntity.ok(new UserRegistrationResponse(jwt));
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginDto userLoginDto) {
        userService.getUserDetails(userLoginDto.getUsername());
    }

    @PostMapping("/admin/add-role")
    public void addRole(@RequestBody RoleDto userRole) {
        System.out.println("inside addrole.");
        userService.addRole(userRole);
    }

    @DeleteMapping("/admin/remove-role")
    public void removeRole(@RequestBody RoleDto userRole) {
        userService.removeRole(userRole);}
}
