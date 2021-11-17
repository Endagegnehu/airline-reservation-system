package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.Tickets;
import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.service.interfaces.TicketsService;
import com.example.airlinereservationsystem.service.TicketsServiceImplementation;
import com.example.airlinereservationsystem.service.interfaces.UserService;
import com.example.airlinereservationsystem.util.security.JwtUtil;
import com.example.airlinereservationsystem.util.security.UserAuth;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TicketsController {

    Logger logger = LoggerFactory.getLogger(TicketsController.class);

    @Autowired
    private TicketsService ticketsService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TicketsServiceImplementation ticketsServiceImpl;

    @RequestMapping(value="/reservations/tickets/{id}", method = RequestMethod.GET)
    public  @ResponseBody
    List<Tickets> getTickets(@PathVariable Long id){
        UserAuth userAuth = new UserAuth();
        User user = userAuth.getUserFromAuth(userService);
        return ticketsService.getTickets(id, user.getID());
    }

}
