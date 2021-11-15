package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.domain.Tickets;
import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.domain.UserRole;
import com.example.airlinereservationsystem.dto.ConfirmationDto;
import com.example.airlinereservationsystem.dto.ReservationsDto;
import com.example.airlinereservationsystem.dto.UserDto;
import com.example.airlinereservationsystem.dto.UserRegistrationResponse;
import com.example.airlinereservationsystem.service.ReservationsService;
import com.example.airlinereservationsystem.service.ReservationsServiceImplementation;
import com.example.airlinereservationsystem.service.TicketsService;
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

import java.math.BigInteger;
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
    TicketsService ticketsService;

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
    
    @PostMapping("/reservations/confirm")
    public  ResponseEntity<?> confirmReservations(@RequestBody ConfirmationDto confirmationDto){
        Tickets ticket = new Tickets();
        final Optional<Reservations> reservation = reservationsService.findReservationsByID(confirmationDto.getReservationId());
        reservation.orElseThrow(()-> new UsernameNotFoundException("No reservation found: "));
        ticket.setReservation(reservation.get());
        ticket.setNumber(genrateRandomString("numeric"));
        ticket.setReservationCode(genrateRandomString("alpha"));
        ticketsService.addTicket(ticket);
        return ResponseEntity.ok().build();

    }

    public static String genrateRandomString(String type) {
        String SALTCHARS = "";
        int size  = 0;
        if(type == "alpha"){
            SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            size = 6;
        }
        else{
            SALTCHARS = "0123456789";
            size = 20;
        }
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    }
    
