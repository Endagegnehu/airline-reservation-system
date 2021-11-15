package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.*;
import com.example.airlinereservationsystem.repository.FlightRespository;
import com.example.airlinereservationsystem.service.interfaces.AirlineService;
import com.example.airlinereservationsystem.service.interfaces.AirportService;
import com.example.airlinereservationsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRespository flightRespository;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirportService airportService;

    @Override
    public List<Flight> findAll() {
        return flightRespository.findAll();
    }

    @Override
    public Page<Flight> findAll(Pageable pageable) {
        return flightRespository.findAll(pageable);
    }

    @Override
    public Flight findById(Long id) {
        return flightRespository.findById(id).orElse(null);
    }

    @Override
    public Flight addFlight(Flight flight) {
        Airline airline = airlineService.getAirlineById(flight.getAirline().getId());
        Airport departureAirport = airportService.getAirportById(flight.getDepartureAirport().getId());
        Airport arrivalAirport = airportService.getAirportById(flight.getArrivalAirport().getId());

        if (airline != null && departureAirport != null && arrivalAirport != null ) {
            flight.setAirline(airline);
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureAirport(departureAirport);
            Flight newFlightObject =  flightRespository.save(flight);
            System.out.println(newFlightObject.getId());
            return newFlightObject;
        } else {
            return null;
        }

    }

    public List<Flight> findSomeByAirports(String departure, String destination) {
        return flightRespository.findSomeByAirports(departure, destination);
    }
}
