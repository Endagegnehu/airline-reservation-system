package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.dto.AirportDto;
import com.example.airlinereservationsystem.service.AddressService;
import com.example.airlinereservationsystem.service.AirlineService;
import com.example.airlinereservationsystem.service.AirportService;
import com.example.airlinereservationsystem.service.interfaces.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping
public class AirportController {
    private final AirportService airportService;
    private final FlightService flightService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAllAirports(){
        log.info("get all airports");
        return ResponseEntity.ok().body(airportService.getAllAirports());
    }
    @GetMapping("/airports/{code}")
    public ResponseEntity<Airport> getAirportByCode(@PathVariable String code){
        log.info("get airport by code {}", code);
        return ResponseEntity.ok().body(airportService.getAirportByCode(code));
    }

    @PostMapping("/admin/airports")
    public ResponseEntity<?> addAirport(@RequestBody AirportDto airportDto){
        log.info("[INFO] AirPORT DTO {}", airportDto);
        Airport airport = modelMapper.map(airportDto, Airport.class);
        Optional<Address> addressOptional = Optional.ofNullable(addressService.getAddress(airportDto.getAddress_id()));
        if (!addressOptional.isPresent()){
            throw new IllegalStateException("Incorrect address id: " + airportDto.getAddress_id());
        }
        airport.setAddress(addressOptional.get());
        airportService.addAirport(airport);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/admin/airports/{code}")
    public ResponseEntity<?> updateAirport(@RequestBody AirportDto airportDto){
        Airport airport = modelMapper.map(airportDto, Airport.class);
        Optional<Address> addressOptional = Optional.ofNullable(addressService.getAddress(airportDto.getAddress_id()));
        if (!addressOptional.isPresent()){
            throw new IllegalStateException("Incorrect address id: " + airportDto.getAddress_id());
        }
        airport.setAddress(addressOptional.get());
        airportService.updateAirport(airport);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/airports/{airportCode}/flights")
    public ResponseEntity<List<Flight>> getAllFlightsByAirportCode(@PathVariable String airportCode){
        return ResponseEntity.ok().body(flightService.getFlightByAirlineCode(airportCode));
    }
}
