package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.FlightInstance;
import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.domain.Tickets;
import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.dto.ConfirmationDto;
import com.example.airlinereservationsystem.dto.ReservationsDto;
import com.example.airlinereservationsystem.service.interfaces.FlightInstanceService;
import com.example.airlinereservationsystem.service.interfaces.ReservationsService;
import com.example.airlinereservationsystem.service.ReservationsServiceImplementation;
import com.example.airlinereservationsystem.service.interfaces.TicketsService;
import com.example.airlinereservationsystem.service.interfaces.UserService;
import com.example.airlinereservationsystem.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
    TicketsService ticketsService;

    @Autowired
    FlightInstanceService flightinstanceService;
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

        long [] ids = reservationsDto.getFlightInstanceIds();
        /*List<FlightInstance > flightInstances = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            final Optional<FlightInstance> flightInstance = flightinstanceService.findById(ids[i]);
            flightInstance.orElseThrow(() -> new UsernameNotFoundException("No reseravation found: "));
            flightInstances.add(flightInstance.get());
        }*/
       //reservations.setFlightInstances(flightInstances);

        reservationsService.addReservation(reservations);
        return ResponseEntity.ok().build();
    }
    /*
    @PostMapping("/reservations/confirm")
    public  ResponseEntity<?> confirmReservations(@RequestBody ConfirmationDto confirmationDto){
        Tickets ticket = new Tickets();
        final Optional<Reservations> reservation = reservationsService.findReservationsByID(confirmationDto.getReservationId());
        reservation.orElseThrow(()-> new UsernameNotFoundException("No reservation found: "));
        Reservations reservationObj = reservation.get();
        // get the flights ids and generate tickets based on that ticket
        ticket.setReservation(reservationObj);
        ticket.setNumber(genrateRandomString("numeric"));
        ticket.setReservationCode(genrateRandomString("alpha"));
        ticketsService.addTicket(ticket);
        return ResponseEntity.ok().build();

    }*/

    public static String genrateRandomString(String type) {
        String SALTCHARS = "";
        int size  = 0;
        if(type.equals("alpha")){
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
        return salt.toString();
    }
    }
    
