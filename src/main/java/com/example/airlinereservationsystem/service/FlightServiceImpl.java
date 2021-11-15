package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.DummyAirline;
import com.example.airlinereservationsystem.domain.DummyAirport;
import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.repository.FlightRespository;
import com.example.airlinereservationsystem.service.interfaces.DummyAirlineService;
import com.example.airlinereservationsystem.service.interfaces.DummyAirportService;
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
    private DummyAirlineService airlineService;

    @Autowired
    private DummyAirportService airportService;

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
        DummyAirline airlineDummy = airlineService.findById(flight.getDummyAirline().getId());
        DummyAirport departureAirportDummy = airportService.findById(flight.getDepartureDummyAirport().getId());
        DummyAirport arrivalAirportDummy = airportService.findById(flight.getArrivalDummyAirport().getId());

        if (airlineDummy != null && departureAirportDummy != null && arrivalAirportDummy != null ) {
            flight.setDummyAirline(airlineDummy);
            flight.setArrivalDummyAirport(arrivalAirportDummy);
            flight.setDepartureDummyAirport(departureAirportDummy);
            Flight newFlightObject =  flightRespository.save(flight);
            return newFlightObject;
        } else {
            return null;
        }

    }

    public List<Flight> findSomeByAirports(String departure, String destination) {
        return flightRespository.findSomeByAirports(departure, destination);
    }
}
