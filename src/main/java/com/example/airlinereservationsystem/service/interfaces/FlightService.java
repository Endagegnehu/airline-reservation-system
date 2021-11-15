package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {
    public List<Flight> findAll();

    public Page<Flight> findAll(Pageable pageable);
    public Flight findById(Long id);
    public void addFlight(Flight flight);
    public List<Flight> findSomeByAirports(String departure, String destination);
}
