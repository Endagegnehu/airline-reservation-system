package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.DummyAirline;
import com.example.airlinereservationsystem.domain.DummyAirport;
import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.service.FlightServiceImpl;
import com.example.airlinereservationsystem.service.interfaces.DummyAirlineService;
import com.example.airlinereservationsystem.service.interfaces.DummyAirportService;
import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightServiceImpl flightService;

    @Autowired
    private DummyAirportService airportService;

    @Autowired
    private DummyAirlineService airlineService;

    @GetMapping("/flights")
    public List<Flight> findAll(){
        return flightService.findAll();
    }

    @GetMapping("/flights/{id}")
    public Flight findById(@PathVariable Long id) {
        return flightService.findById(id);
    }

    @PostMapping(path = "/admin/flights", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addFlight(@RequestBody Flight flight){
        DummyAirline airlineDummy = airlineService.findById(flight.getDummyAirline().getId());
        DummyAirport departureAirportDummy = airportService.findById(flight.getDepartureDummyAirport().getId());
        DummyAirport arrivalAirportDummy = airportService.findById(flight.getArrivalDummyAirport().getId());
        if (airlineDummy != null && departureAirportDummy != null && arrivalAirportDummy != null ) {
            flight.setDummyAirline(airlineDummy);
            flight.setArrivalDummyAirport(arrivalAirportDummy);
            flight.setDepartureDummyAirport(departureAirportDummy);
            flightService.addFlight(flight);
            return new ResponseEntity<String>(HttpStatus.CREATED);
//            return (ResponseEntity<String>) ResponseEntity.created();
        } else
            return new ResponseEntity<String>("Null Entities", HttpStatus.BAD_REQUEST);
    }

}

