package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.dto.FlightDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {
    public List<Flight> findAll();

    public Page<Flight> findAll(Pageable pageable);
    public Flight findById(Long id);
    public Flight addFlight(FlightDto flightDto);
    public List<Flight> findSomeByAirports(String departure, String destination);
    public Flight updateFlightProperty(Long id, FlightDto flightDto);
    public boolean removeFlight(Long id);
}
