package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.domain.UserRole;
import com.example.airlinereservationsystem.dto.ReservationsDto;
import com.example.airlinereservationsystem.dto.UserDto;
import com.example.airlinereservationsystem.dto.UserRegistrationResponse;
import com.example.airlinereservationsystem.service.ReservationsService;
import com.example.airlinereservationsystem.service.ReservationsServiceImplementation;
import com.example.airlinereservationsystem.service.UserService;
import com.example.airlinereservationsystem.util.JwtUtil;
import com.example.airlinereservationsystem.util.UserSecurityDetailsImpl;
import com.example.airlinereservationsystem.util.constant.Roles;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping
public class ReservationsController {
    Logger logger = LoggerFactory.getLogger(TicketsController.class);
    @Autowired
    ReservationsService reservationsService;
    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ReservationsServiceImplementation reservationsServiceImpl;
    
    @PostMapping("/reservations")
    public  ResponseEntity<?> addReservation(@RequestBody ReservationsDto reservationsDto){
        Reservations reservations = new Reservations();
        final Optional<User> user = userService.findUserByID(reservationsDto.getUserId());
        user.orElseThrow(()-> new UsernameNotFoundException("No user found: "));
        reservations.setUser(user.get());
        reservationsService.addReservation(reservations);
        return ResponseEntity.ok().build();
    }
}
