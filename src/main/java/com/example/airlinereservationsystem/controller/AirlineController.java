package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.dto.AirlineDto;
import com.example.airlinereservationsystem.service.interfaces.AirlineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class AirlineController {
    private final AirlineService AirLineService;
    private final ModelMapper modelMapper;

    @GetMapping("/airlines")
    public ResponseEntity<List<Airline>> getAllAirports(){
        log.info("[INFO] get all airlines");
        return ResponseEntity.ok().body(AirLineService.getAllAirlines());
    }
    @GetMapping("/airlines/{code}")
    public ResponseEntity<Airline> getAirportByCode(@PathVariable String code){
        log.info("[INFO] get airline by code {}", code);
        return ResponseEntity.ok().body(AirLineService.getAirlineByCode(code));
    }
    @PostMapping("/admin/airlines")
    public ResponseEntity<?> addAirline(@RequestBody AirlineDto airlineDto){
        Airline airline = modelMapper.map(airlineDto, Airline.class);
        AirLineService.addAirline(airline);
        log.info("[INFO] adding Airline {}", airline);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/admin/airlines/{code}")
    public ResponseEntity<?> updateAirline(@RequestBody AirlineDto airlineDto, @PathVariable String code){
        Airline airline = modelMapper.map(airlineDto, Airline.class);
        AirLineService.updateAirline(airline);
        log.info("[INFO] updating airline with code {}", airline.getCode());
        return ResponseEntity.ok().build();
    }
}