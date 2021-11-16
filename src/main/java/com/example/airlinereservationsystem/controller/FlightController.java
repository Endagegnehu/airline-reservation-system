package com.example.airlinereservationsystem.controller;

import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.dto.FlightDto;
import com.example.airlinereservationsystem.service.FlightServiceImpl;
import com.example.airlinereservationsystem.util.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(value = "/flights")
    public List<Flight> findAll(){
        return flightService.findAll();
    }


    @GetMapping(value = "/flights", params = "paged=true")
    public Page<Flight> findAll(Pageable pageable){
        return flightService.findAll(pageable);
    }

    @GetMapping("/flights/{id}")
    public Flight findById(@PathVariable Long id) {
        return flightService.findById(id);
    }

    @GetMapping(value = "/flights", params = {"dep", "dest"})
    @ResponseBody
    public List<Flight> findSomeByAirports(@RequestParam(name = "dep", required = false) String departureAirport,
                                 @RequestParam(name = "dest", required = false) String destinationAirport){

        return flightService.findSomeByAirports(departureAirport, destinationAirport);
    }

    @PostMapping(path = "/admin/flights", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addFlight(@RequestBody FlightDto flightDto){
        Flight flight = flightService.addFlight(flightDto);
        if ( flight != null){
            return  ResponseHandler.respond("Successfully added a flight!", HttpStatus.OK, flight);
        } else {
            return  ResponseHandler.respond("Null entities found", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/admin/flights/{id}", consumes = "application/json")
    public ResponseEntity<?> updateFlight(@PathVariable Long id,  @RequestBody FlightDto flightDto){
        Flight flight = flightService.updateFlightProperty(id, flightDto);
        return  ResponseHandler.respond("Successfully updated a flight!", HttpStatus.ACCEPTED, flight);
    }

    @DeleteMapping(path="/admin/flights/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id){
        flightService.removeFlight(id);
        return  ResponseHandler.respond("Successfully deleted a flight!", HttpStatus.ACCEPTED);
    }
}

