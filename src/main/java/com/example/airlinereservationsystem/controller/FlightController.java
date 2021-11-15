package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.dto.FlightDto;
import com.example.airlinereservationsystem.service.AirlineService;
import com.example.airlinereservationsystem.service.AirportService;
import com.example.airlinereservationsystem.service.FlightServiceImpl;
import com.example.airlinereservationsystem.util.ResponseHandler;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirlineService airlineService;

    @GetMapping("/flights")
    public List<Flight> findAll(){
        return flightService.findAll();
    }

    @GetMapping("/flights/{id}")
    public Flight findById(@PathVariable Long id) {
        return flightService.findById(id);
    }

    @PostMapping(path = "/admin/flights", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addFlight(@RequestBody FlightDto flightDto){
        Airline airline = airlineService.getAirlineById(flightDto.getAirline().getId());
        Airport departureAirport = airportService.getById(flightDto.getDepartureAirport().getId());
        Airport arrivalAirport = airportService.getById(flightDto.getArrivalAirport().getId());
        if (airline != null && departureAirport != null && arrivalAirport != null ) {
            Flight flight = modelMapper.map(flightDto, Flight.class);
            flight.setAirline(airline);
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureAirport(departureAirport);
            flightService.addFlight(flight);
            return  ResponseHandler.respond("Successfully added a flight!", HttpStatus.OK, flight);
        } else
            return  ResponseHandler.respond("Null entities found", HttpStatus.BAD_REQUEST);

    }

}

