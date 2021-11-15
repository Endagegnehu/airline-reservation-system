package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.DummyAirline;
import com.example.airlinereservationsystem.domain.DummyAirport;
import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.dto.FlightDto;
import com.example.airlinereservationsystem.service.FlightServiceImpl;
import com.example.airlinereservationsystem.service.interfaces.DummyAirlineService;
import com.example.airlinereservationsystem.service.interfaces.DummyAirportService;
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
    private DummyAirportService airportService;

    @Autowired
    private DummyAirlineService airlineService;

    @GetMapping(value = "/flights")
    public List<Flight> findAll(){
        return flightService.findAll();
    }

    @GetMapping("/flights/{id}")
    public Flight findById(@PathVariable Long id) {
        return flightService.findById(id);
    }

    @GetMapping(value = "/flights", params = {"dep", "dest"})
    @ResponseBody
    public List<Flight> findSome(@RequestParam(name = "dep", required = false) String departureAirport,
                                 @RequestParam(name = "dest", required = false) String destinationAirport){

        return flightService.findSomeByAirports(departureAirport, destinationAirport);
    }

    @PostMapping(path = "/admin/flights", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addFlight(@RequestBody FlightDto flightDto){
        DummyAirline airlineDummy = airlineService.findById(flightDto.getDummyAirline().getId());
        DummyAirport departureAirportDummy = airportService.findById(flightDto.getDepartureDummyAirport().getId());
        DummyAirport arrivalAirportDummy = airportService.findById(flightDto.getArrivalDummyAirport().getId());
        if (airlineDummy != null && departureAirportDummy != null && arrivalAirportDummy != null ) {
            Flight flight = modelMapper.map(flightDto, Flight.class);
            flight.setDummyAirline(airlineDummy);
            flight.setArrivalDummyAirport(arrivalAirportDummy);
            flight.setDepartureDummyAirport(departureAirportDummy);
            flightService.addFlight(flight);
            return  ResponseHandler.respond("Successfully added a flight!", HttpStatus.OK, flight);
        } else
            return  ResponseHandler.respond("Null entities found", HttpStatus.BAD_REQUEST);

    }

}

