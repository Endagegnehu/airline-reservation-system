package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface AirportService {
    List<Airport> getAllAirports();
    Airport getAirportByCode(String code);
    Airport getById(Long id);

    void addAirport(Airport airport);
    void updateAirport(Airport airport);

    void deleteAirport(String code);

    Page<Airport> getAllAirportsPages(Pageable page);
}
