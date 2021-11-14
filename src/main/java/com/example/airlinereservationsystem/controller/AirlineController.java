package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.service.AirlineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/airlines")
@Slf4j
public class AirlineController {
    private final AirlineService AirLineService;


    @GetMapping
    public ResponseEntity<List<Airline>> getAllAirports(){
        log.info("get all airlines");
        return ResponseEntity.ok().body(AirLineService.getAllAirlines());
    }
    @GetMapping("/{code}")
    public ResponseEntity<Airline> getAirportByCode(@PathVariable String code){
        log.info("get airline by code {}", code);
        return ResponseEntity.ok().body(AirLineService.getAirlineByCode(code));
    }
    @PostMapping
    public ResponseEntity<?> addAirline(@RequestBody Airline airline){
        AirLineService.addAirline(airline);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{code}")
    public ResponseEntity<?> updateAirline(@RequestBody Airline airline){
        AirLineService.updateAirline(airline);
        return ResponseEntity.ok().build();
    }

}