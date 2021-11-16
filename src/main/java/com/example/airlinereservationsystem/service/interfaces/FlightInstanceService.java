package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.domain.FlightInstance;
import com.example.airlinereservationsystem.dto.FlightDto;
import com.example.airlinereservationsystem.dto.FlightInstanceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

public interface FlightInstanceService {
    public Page<FlightInstance> findAll(Pageable pageable);
    public Page<FlightInstance> findAllPerFlight(Long id, Pageable pageable);
    public Page<FlightInstance> findAllBetweenTwoDestinationsOnADate(String departureAirport, String arrivalAirport, LocalDate date, Pageable pageable);
    Optional<FlightInstance> findById(long id);

    public FlightInstance addOnaFlight(Long id, FlightInstanceDto flightInstanceDto);
}
