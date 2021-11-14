package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.service.TicketsService;
import com.example.airlinereservationsystem.service.TicketsServiceImplementation;
import com.example.airlinereservationsystem.service.UserService;
import com.example.airlinereservationsystem.service.UserServiceImplementation;
import com.example.airlinereservationsystem.util.JwtUtil;
import com.example.airlinereservationsystem.util.constant.Roles;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class TicketsController {
    Logger logger = LoggerFactory.getLogger(TicketsController.class);
    @Autowired
    TicketsService ticketsService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TicketsServiceImplementation ticketsServiceImpl;

}
