package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.domain.UserRole;
import com.example.airlinereservationsystem.dto.RoleDto;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import com.example.airlinereservationsystem.dto.UserDto;
import com.example.airlinereservationsystem.dto.UserRegistrationResponse;
import com.example.airlinereservationsystem.service.interfaces.UserService;
import com.example.airlinereservationsystem.util.JwtUtil;
import com.example.airlinereservationsystem.util.constant.Roles;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setRoleName(Roles.ROLE_USER);
        userRoles.add(userRole);
        User user = modelMapper.map(userDto,User.class);
        user.setRole(userRoles);
        ResponseEntity<?> responseEntity = userService.signup(user);
        if(responseEntity.getStatusCode() == HttpStatus.CONFLICT){
            return ResponseEntity.ok(new HashMap<>(){{put( responseEntity.getStatusCode(),responseEntity.getBody());}} );
        }else{
            final UserDetails userDetails = userService.getUserDetails(user.getUsername());
            final Map<String, Object> jwt  = jwtUtil.generateToken(userDetails);
            logger.info("JWT: " + jwt);
            return ResponseEntity.ok(new UserRegistrationResponse(jwt));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        userService.getUserDetails(userLoginDto.getUsername());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/add-role")
    public ResponseEntity<?> addRole(@RequestBody RoleDto userRole) {
        System.out.println("inside addrole.");
        userService.addRole(userRole);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/remove-role")
    public ResponseEntity<?> removeRole(@RequestBody RoleDto userRole) {
        System.out.println("inside removeRole controller. ");
        userService.removeRole(userRole);
        return ResponseEntity.ok().build();
    }
}
