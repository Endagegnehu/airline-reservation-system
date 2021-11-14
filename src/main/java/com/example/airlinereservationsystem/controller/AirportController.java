package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.service.AirlineService;
import com.example.airlinereservationsystem.service.AirportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;
    private final AirlineService AirLineService;

    @GetMapping()
    public ResponseEntity<List<Airport>> getAllAirports(){
        log.info("get all airports");
        return ResponseEntity.ok().body(airportService.getAllAirports());
    }
    @GetMapping("/{code}")
    public ResponseEntity<Airport> getAirportByCode(@PathVariable String code){
        log.info("get airport by code {}", code);
        return ResponseEntity.ok().body(airportService.getAirportByCode(code));
    }

    @PostMapping
    public ResponseEntity<?> addAirport(@RequestBody Airport airport){
        airportService.addAirport(airport);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{code}")
    public ResponseEntity<?> updateAirport(@RequestBody Airport airport){
        airportService.updateAirport(airport);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{airportCode}/flights")
    public ResponseEntity<List<Airline>> getAllFlightsByAirportCode(@PathVariable String airportCode){
        return null;
    }
}
