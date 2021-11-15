package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> getAllAirports();
    Airport getAirportByCode(String code);

    void addAirport(Airport airport);
    void updateAirport(Airport airport);

    void deleteAirport(String code);
}
