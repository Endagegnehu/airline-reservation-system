package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.Tickets;
import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.dto.TicketsResponseDto;
import com.example.airlinereservationsystem.service.interfaces.TicketsService;
import com.example.airlinereservationsystem.service.TicketsServiceImpl;
import com.example.airlinereservationsystem.service.interfaces.UserService;
import com.example.airlinereservationsystem.util.ResponseHandler;
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
    private UserService userService;

    @RequestMapping(value="/reservations/tickets/{id}", method = RequestMethod.GET)
    public  @ResponseBody
    TicketsResponseDto getTickets(@PathVariable Long id){
        UserAuth userAuth = new UserAuth();
        User user = userAuth.getUserFromAuth(userService);
        List<Tickets> tickets = ticketsService.getTickets(id, user.getID());
        return ResponseHandler.modifyTickets(tickets);
    }

}
