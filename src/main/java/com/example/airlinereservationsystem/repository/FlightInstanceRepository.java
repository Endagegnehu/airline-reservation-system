package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.domain.FlightInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface FlightInstanceRepository extends JpaRepository<FlightInstance, Long> {
    @Query("SELECT fi FROM FlightInstance fi WHERE fi.flight.id = :id")
    public Page<FlightInstance> findAllPerFlight(@Param("id") Long id, Pageable pageable);

}
