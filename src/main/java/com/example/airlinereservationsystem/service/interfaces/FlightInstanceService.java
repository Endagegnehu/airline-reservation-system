package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.domain.FlightInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightInstanceService {
    public Page<FlightInstance> findAll(Pageable pageable);
    public Page<FlightInstance> findAllPerFlight(Long id, Pageable pageable);
}
