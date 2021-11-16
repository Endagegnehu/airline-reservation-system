package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.FlightInstance;
import com.example.airlinereservationsystem.dto.FlightInstanceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface FlightInstanceService {
    Page<FlightInstance> findAll(Pageable pageable);
    Page<FlightInstance> findAllPerFlight(Long id, Pageable pageable);
    Page<FlightInstance> findAllBetweenTwoDestinationsOnADate(String departureAirport, String arrivalAirport, LocalDate date, Pageable pageable);
    FlightInstance addOnaFlight(Long id, FlightInstanceDto flightInstanceDto);
    FlightInstance findOnePerFlight(Long id, Long instanceId);
    void removeInstance(Long id);
}
