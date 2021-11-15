package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.repository.FlightRespository;
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
    public void addFlight(Flight flight) {
        flightRespository.save(flight);
    }

    public List<Flight> findSomeByAirports(String departure, String destination) {
        return flightRespository.findSomeByAirports(departure, destination);
    }
}
