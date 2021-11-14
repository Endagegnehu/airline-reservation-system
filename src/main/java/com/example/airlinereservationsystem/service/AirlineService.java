package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Airline;

import java.util.List;

public interface AirlineService {
    List<Airline> getAllAirlines();
    Airline getAirlineByCode(String code);
    void addAirline(Airline airline);
    void updateAirline(Airline airline);

    void deleteAirline(String code);
}
