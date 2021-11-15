package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.service.interfaces.TicketsService;
import com.example.airlinereservationsystem.service.TicketsServiceImplementation;
import com.example.airlinereservationsystem.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
