package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.dto.FlightDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {
    List<Flight> findAll();
    Page<Flight> findAll(Pageable pageable);
    Flight findById(Long id);
    Flight addFlight(FlightDto flightDto);
    List<Flight> findAllByAirports(String departure, String destination);
    Flight updateFlight(Long id, FlightDto flightDto);
    void removeFlight(Long id);
    List<Flight> getAllFlightByDepartureAirportCode(String code);
    List<Flight> findAllByAirlineCode(String code);
    List<Flight> findAllByDepartureAirportCodeAndAirlineCode(String airlineCode, String airportCode);

}
