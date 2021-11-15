package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.FlightInstance;
import com.example.airlinereservationsystem.service.interfaces.FlightInstanceService;
import com.example.airlinereservationsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightInstanceController {
    @Autowired
    FlightService flightService;

    @Autowired
    FlightInstanceService instanceService;

    @GetMapping(value = "/all", params = "paged=true")
    public Page<FlightInstance> findAll(Pageable pageable){
        // exception handler here
        return instanceService.findAll(pageable);
    }

    @GetMapping(value = "/{id}/instances", params = "paged=true")
    public Page<FlightInstance> findAllPerFlight(@PathVariable Long id, Pageable pageable){
        // exception handler here
        return instanceService.findAllPerFlight(id, pageable);
    }
}
