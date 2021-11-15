package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.dto.AirportDto;
import com.example.airlinereservationsystem.service.AddressService;
import com.example.airlinereservationsystem.service.AirlineService;
import com.example.airlinereservationsystem.service.AirportService;
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
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;
    private final AirlineService AirLineService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

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
    public ResponseEntity<?> addAirport(@RequestBody AirportDto airportDto){
        Airport airport = modelMapper.map(airportDto, Airport.class);
        Optional<Address> addressOptional = Optional.ofNullable(addressService.getAddresById(airportDto.getAddress_id()));
        if (!addressOptional.isPresent()){
            throw new IllegalStateException("Incorrect address id: " + airportDto.getAddress_id());
        }
        airport.setAddress(addressOptional.get());
        airportService.addAirport(airport);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{code}")
    public ResponseEntity<?> updateAirport(@RequestBody AirportDto airportDto){
        Airport airport = modelMapper.map(airportDto, Airport.class);
        Optional<Address> addressOptional = Optional.ofNullable(addressService.getAddresById(airportDto.getAddress_id()));
        if (!addressOptional.isPresent()){
            throw new IllegalStateException("Incorrect address id: " + airportDto.getAddress_id());
        }
        airport.setAddress(addressOptional.get());
        airportService.updateAirport(airport);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{airportCode}/flights")
    public ResponseEntity<List<Airline>> getAllFlightsByAirportCode(@PathVariable String airportCode){
        return null;
    }
}
