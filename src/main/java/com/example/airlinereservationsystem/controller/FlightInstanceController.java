package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.FlightInstance;
import com.example.airlinereservationsystem.service.interfaces.FlightInstanceService;
import com.example.airlinereservationsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/flights")
public class FlightInstanceController {
    @Autowired
    FlightService flightService;

    @Autowired
    FlightInstanceService instanceService;

    @GetMapping(value = "/instances", params = "paged=true")
    public Page<FlightInstance> findAll(Pageable pageable){
        // exception handler here
        return instanceService.findAll(pageable);
    }

    @GetMapping(value = "/{id}/instances", params = "paged=true")
    public Page<FlightInstance> findAllPerFlight(@PathVariable Long id, Pageable pageable){
        // exception handler here
        return instanceService.findAllPerFlight(id, pageable);
    }

    @GetMapping(value = "/instances", params = {"paged=true", "dep", "dest", "date"})
    public Page<FlightInstance> findAllBetweenTwoDestinationsOnADate(@RequestParam(name = "dep", required = false)String departureAirport,
                                                                     @RequestParam(name = "dest", required = false) String arrivalAirport,
                                                                     @RequestParam(name = "date", required = false) String date,
                                                                     Pageable pageable){
        // error handler here date format 2018-05-05
        return instanceService.findAllBetweenTwoDestinationsOnADate(departureAirport, arrivalAirport, LocalDate.parse(date), pageable);
    }

}
